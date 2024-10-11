package com.example.newstatussaver.data

import android.content.Context
import com.example.newstatussaver.utils.BookmarkPreferences

class BookmarkRepository(private val context: Context) {
    fun addBookmark(verse: Verse){
        BookmarkPreferences.addBookmark(context,verse)
    }

    fun getBookmarks(): List<Verse>{
        return BookmarkPreferences.getBookmarks(context)
    }
}
