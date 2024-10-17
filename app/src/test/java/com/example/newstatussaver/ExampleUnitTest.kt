package com.example.newstatussaver

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

//@Test
//fun isIsogram(word: String): Any {
//    // Normalize the word: convert to lowercase and filter alphabetic characters only
//    val normalizedWord = word.lowercase().filter { it.isLetter() }
//
//    // Create a mutable list to track repeated letters
//    val repeatedLetters = mutableListOf<Char>()
//
//    // Create a mutable set to track unique letters
//    val letterSet = mutableSetOf<Char>()
//
//    // Iterate through the letters of the word
//    for (char in normalizedWord) {
//        if (char in letterSet) {
//            if (!repeatedLetters.contains(char)) {
//                repeatedLetters.add(char)
//            }
//        } else {
//            letterSet.add(char)
//        }
//    }
//
//    // Return true if no repeated letters were found, else return the list of repeated letters
//    return if (repeatedLetters.isEmpty()) true else repeatedLetters
//}
//
//fun main() {
//    println(isIsogram("tomorrow"))  // Output: [o, r]
//    println(isIsogram("isogram"))   // Output: true
//    println(isIsogram("Alphabet"))  // Output: true
//    println(isIsogram("hello world!"))  // Output: [l, o]
//}
