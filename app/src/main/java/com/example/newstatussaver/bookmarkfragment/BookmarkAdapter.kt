package com.example.newstatussaver.bookmarkfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newstatussaver.data.Verse
import com.example.newstatussaver.databinding.ItemBookmarkBinding

class BookmarkAdapter(private var bookmarks: List<Verse>) :  RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>(){
    class BookmarkViewHolder(private val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(verse: Verse) {
            binding.verseText.text = verse.text.arab
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = bookmarks[position]
        holder.bind(bookmark)
    }

    override fun getItemCount(): Int = bookmarks.size
    fun updateBookmarks(newBookmarks: List<Verse>) {
        bookmarks = newBookmarks
        Log.d("BookmarkAdapter", "Bookmarks updated with ${bookmarks.size} items")
        notifyDataSetChanged()
    }
}
