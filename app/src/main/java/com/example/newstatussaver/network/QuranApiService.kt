package com.example.newstatussaver.network

import com.example.newstatussaver.data.QuranAPI
import retrofit2.Response
import retrofit2.http.GET


interface QuranApiService {
    @GET("surah")
    suspend fun getQuranData(): Response<QuranAPI>
}

//    @GET("surah/{surah}")
//    suspend fun getSpecificSurah(@Path("surah") surah: Int): Response<SurahAPI>
//
//    @GET("surah/{surah}/{ayah}")
//    suspend fun getSpecificAyahInSurah(@Path("surah") surah: Int, @Path("ayah") ayah: Int): Response<SurahAPI>
//
//    @GET("juz/{juz}")
//    suspend fun getSpecificJuz(@Path("juz") juz: Int): Response<SurahAPI>

