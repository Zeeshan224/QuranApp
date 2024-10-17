package com.example.newstatussaver

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstatussaver.data.Data
import com.example.newstatussaver.data.QuranRepository
import com.example.newstatussaver.data.SurahData
import com.example.newstatussaver.data.Verse
import com.example.newstatussaver.network.RetrofitInstance
import kotlinx.coroutines.launch

class QuranViewModel : ViewModel() {

    private val _bookmarkedVerses = MutableLiveData<List<Verse>>(emptyList())
    val bookmarkedVerses: LiveData<List<Verse>> get() = _bookmarkedVerses
    private val repository: QuranRepository by lazy {
        QuranRepository(RetrofitInstance.api)
    }
     fun addBookmark(verse: Verse) {
        viewModelScope.launch {
            Log.d("QuranViewModel", "Adding bookmark for verse: ${verse.text}")  // Log the verse text
            val updatedBookmarks = _bookmarkedVerses.value.orEmpty().toMutableList().apply {
                add(verse)
            }
            _bookmarkedVerses.postValue(updatedBookmarks)
            Log.d("QuranViewModel", "Current bookmarks count: ${updatedBookmarks.size}")
        }
    }

    private val _surahList = MutableLiveData<List<Data>>()
    val surahList: LiveData<List<Data>> get() = _surahList

    //Fetching Surah Data

    private val _surahData = MutableLiveData<SurahData?>()
    val surahData: LiveData<SurahData?> get() = _surahData

    fun fetchSurahList() {
        viewModelScope.launch {
            val surahs = repository.getQuranData()
            _surahList.postValue(surahs)
            // Only post to LiveData if the API call was successful
        }
    }

    fun fetchSurah(surah: Int) {
        viewModelScope.launch {
            val surahData = repository.getFullSurah(surah)
            _surahData.postValue(surahData)
            // Only post to LiveData if the API call was successful
        }
    }
}