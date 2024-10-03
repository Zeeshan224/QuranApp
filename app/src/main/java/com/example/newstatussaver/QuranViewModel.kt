package com.example.newstatussaver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstatussaver.data.Data
import com.example.newstatussaver.data.QuranRepository
import com.example.newstatussaver.network.RetrofitInstance
import kotlinx.coroutines.launch

class QuranViewModel : ViewModel() {

    private val repository: QuranRepository by lazy {
        QuranRepository(RetrofitInstance.api)
    }

    private val _surahList = MutableLiveData<List<Data>>()
    val surahList: LiveData<List<Data>> get() = _surahList

    fun fetchSurahList() {
        viewModelScope.launch {
            val surahs = repository.getQuranData()
            _surahList.postValue(surahs)
            // Only post to LiveData if the API call was successful
        }
    }
}