package com.example.newstatussaver

import android.content.Context
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class QuranViewModel : ViewModel() {

    private val _bookmarkedVerses = MutableLiveData<List<Verse>>(emptyList())
    val bookmarkedVerses: LiveData<List<Verse>> get() = _bookmarkedVerses

    private val repository: QuranRepository by lazy {
        QuranRepository(RetrofitInstance.api)
    }

    fun loadBookmarks(context: Context) {
        _bookmarkedVerses.value = getBookmarks(context)
    }

    private fun getBookmarks(context: Context): List<Verse> {
        val sharedPreferences = context.getSharedPreferences("BookmarkPrefs", Context.MODE_PRIVATE)
        val jsonBookmarks = sharedPreferences.getString("bookmarkedVerses", null)

        return if (jsonBookmarks != null) {
            val gson = Gson()
            val type = object : TypeToken<List<Verse>>() {}.type
            gson.fromJson(jsonBookmarks, type)
        } else {
            emptyList()
        }
    }

    fun toggleBookmarks(verse: Verse, context: Context){
        val currentBookmarks = getBookmarks(context).toMutableList()

        if (currentBookmarks.contains(verse)){
            currentBookmarks.remove(verse)
        }
        else{
            currentBookmarks.add(verse)
        }
        saveBookmarks(context, currentBookmarks)
        _bookmarkedVerses.value = currentBookmarks
    }

    fun addBookmark(verse: Verse, context: Context) {
        val currentBookmarks = getBookmarks(context).toMutableList()

        // Check if the verse is already bookmarked
        if (!currentBookmarks.contains(verse)) {
            currentBookmarks.add(verse)
            saveBookmarks(context, currentBookmarks)
            _bookmarkedVerses.value = currentBookmarks // Update the LiveData
        }
    }

    private fun saveBookmarks(context: Context, bookmarks: List<Verse>) {
        val sharedPreferences = context.getSharedPreferences("BookmarkPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonBookmarks = gson.toJson(bookmarks)
        editor.putString("bookmarkedVerses", jsonBookmarks)
        editor.apply()
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