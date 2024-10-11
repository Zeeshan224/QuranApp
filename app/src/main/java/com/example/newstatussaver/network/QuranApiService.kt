package com.example.newstatussaver.network

import com.example.newstatussaver.data.QuranAPI
import com.example.newstatussaver.data.SurahDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface QuranApiService {
    @GET("surah")
    suspend fun getQuranData(): Response<QuranAPI>

    @GET("surah/{surah}")
    suspend fun getSpecificSurah(@Path("surah") surah: Int): Response<SurahDataModel>
}
//
//    @GET("surah/{surah}/{ayah}")
//    suspend fun getSpecificAyahInSurah(@Path("surah") surah: Int, @Path("ayah") ayah: Int): Response<SurahAPI>
//
//    @GET("juz/{juz}")
//    suspend fun getSpecificJuz(@Path("juz") juz: Int): Response<SurahAPI>

