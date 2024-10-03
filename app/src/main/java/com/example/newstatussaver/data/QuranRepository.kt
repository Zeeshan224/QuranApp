package com.example.newstatussaver.data

import android.util.Log
import com.example.newstatussaver.network.QuranApiService

class QuranRepository(private val apiService: QuranApiService) {

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
}
