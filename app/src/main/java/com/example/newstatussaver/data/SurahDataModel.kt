package com.example.newstatussaver.data

data class SurahDataModel(
    val code: Int,
    val `data`: SurahData,
    val message: String,
    val status: String
)

data class SurahData(
    val name: SurahName,
    val number: Int,
    val numberOfVerses: Int,
    val preBismillah: Any,
    val revelation: SurahRevelation,
    val sequence: Int,
    val tafsir: SurahTafsir,
    val verses: List<Verse>
)

data class SurahName(
    val long: String,
    val short: String,
    val translation: SurahTranslation,
    val transliteration: SurahTransliteration
)

data class SurahRevelation(
    val arab: String,
    val en: String,
    val id: String
)

data class SurahTafsir(
    val id: String
)

data class Verse(
    val audio: Audio,
    val meta: Meta,
    val number: Number,
    val tafsir: TafsirX,
    val text: Text,
    val translation: SurahTranslation,
    var isBookmarked: Boolean
)

data class SurahTranslation(
    val en: String,
    val id: String
)

data class SurahTransliteration(
    val en: String,
    val id: String
)

data class Audio(
    val primary: String,
    val secondary: List<String>
)

data class Meta(
    val hizbQuarter: Int,
    val juz: Int,
    val manzil: Int,
    val page: Int,
    val ruku: Int,
    val sajda: Sajda
)

data class Number(
    val inQuran: Int,
    val inSurah: Int
)

data class TafsirX(
    val id: Id
)

data class Text(
    val arab: String,
    val transliteration: TransliterationX
)

data class Sajda(
    val obligatory: Boolean,
    val recommended: Boolean
)

data class Id(
    val long: String,
    val short: String
)

data class TransliterationX(
    val en: String
)
