package com.example.newstatussaver.network

import com.example.newstatussaver.data.QuranAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.quran.gading.dev/" // Replace with your base URL

    val api: QuranApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson to parse JSON
            .build()
            .create(QuranApiService::class.java)
    }
}