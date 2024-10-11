package com.example.newstatussaver.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.newstatussaver.data.Verse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BookmarkPreferences {

    private const val PREFS_NAME = "BookmarksPrefs"
    private const val KEY_BOOKMARKS = "bookmarks"

    private fun getSharedPreferences(context: Context):SharedPreferences{
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveBookmarks(context: Context,bookmarks: List<Verse>){
        val json = Gson().toJson(bookmarks)
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_BOOKMARKS,json)
        editor.apply()
    }

    fun getBookmarks(context: Context):List<Verse>{
        val json = getSharedPreferences(context).getString(KEY_BOOKMARKS,null)
        val type = object : TypeToken<List<Verse>>(){}.type
        return if (json != null){
            Gson().fromJson(json,type)
        }
        else{
            emptyList()
        }
    }
    fun addBookmark(context: Context,verse: Verse){
        val currentBookmarks = getBookmarks(context).toMutableList()
        if (!currentBookmarks.contains(verse)) {
            currentBookmarks.add(verse)
            saveBookmarks(context, currentBookmarks)
        }
    }
}
