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
    val tafsir: Tafsir,
    val verses: List<Ayah>
) : Parcelable

@Parcelize
data class Ayah(
    val number: Int,       // Verse number
    val arabicText: String
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
