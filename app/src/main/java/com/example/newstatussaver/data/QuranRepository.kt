package com.example.newstatussaver.data

import android.util.Log
import com.example.newstatussaver.network.QuranApiService

class QuranRepository(private val apiService: QuranApiService) {

    private val bookmarks = mutableListOf<Verse>()

    fun addBookmark(verse: Verse) {
        if (!bookmarks.contains(verse)) {
            verse.isBookmarked = true
            bookmarks.add(verse)
        }
    }

    fun getBookmarks(): List<Verse> = bookmarks
    suspend fun getQuranData(): List<Data> {
        return try {
            val response = apiService.getQuranData()
            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d("QuranRepository","Response : $responseBody")

                responseBody?.data ?: emptyList()// Return the data if successful

            } else {
                Log.e("QuranRepository", "API call failed: ${response.errorBody()?.string()}")
                emptyList() // Return null if the response failed
            }
        } catch (e: Exception) {
            Log.e("QuranRepository", "Exception occurred: ${e.message}")
            emptyList() // Return null in case of an exception
        }
    }

    suspend fun getFullSurah(surah : Int) : SurahData?{
        var surahData : SurahData? = null
        val response = apiService.getSpecificSurah(surah)
        if (response.isSuccessful) {
            val responseBody = response.body()
            Log.d("SurahRepository","Response : $responseBody")

            surahData = responseBody?.data // Return the data if successful

        } else {
            Log.e("SurahRepository", "API call failed: ${response.errorBody()?.string()}")
             // Return null if the response failed
        }

        return surahData
    }
}
