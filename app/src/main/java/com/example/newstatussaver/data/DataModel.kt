package com.example.newstatussaver.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuranAPI(
    val code: Int,
    val `data`: List<Data>,
    val message: String,
    val status: String
) : Parcelable

@Parcelize
data class Data(
    val name: Name,
    val number: Int,
    val numberOfVerses: Int,
    val revelation: Revelation,
    val sequence: Int,
    val tafsir: Tafsir
) : Parcelable

@Parcelize
data class Name(
    val long: String,
    val short: String,
    val translation: Translation,
    val transliteration: Transliteration
) : Parcelable

@Parcelize
data class Revelation(
    val arab: String,
    val en: String,
    val id: String
) : Parcelable

@Parcelize
data class Tafsir(
    val id: String
) : Parcelable

@Parcelize
data class Translation(
    val en: String,
    val id: String
) : Parcelable

@Parcelize
data class Transliteration(
    val en: String,
    val id: String
) : Parcelable


//
//data class QuranAPI(
//    val code: Int,
//    val `data`: List<Data>,
//    val message: String,
//    val status: String
//)
//
//data class Data(
//    val name: Name,
//    val number: Int,
//    val numberOfVerses: Int,
//    val revelation: Revelation,
//    val sequence: Int,
//    val tafsir: Tafsir
//)
//
//data class Name(
//    val long: String,
//    val short: String,
//    val translation: Translation,
//    val transliteration: Transliteration
//)
//
//data class Revelation(
//    val arab: String,
//    val en: String,
//    val id: String
//)
//
//data class Tafsir(
//    val id: String
//)
//
//data class Translation(
//    val en: String,
//    val id: String
//)
//
//data class Transliteration(
//    val en: String,
//    val id: String
//)
//
