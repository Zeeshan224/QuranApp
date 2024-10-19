package com.example.newstatussaver.bookmarkfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstatussaver.QuranViewModel
import com.example.newstatussaver.R
import com.example.newstatussaver.data.Verse
import com.example.newstatussaver.databinding.FragmentBookmarkBinding
import com.example.newstatussaver.databinding.FragmentSurahListBinding

class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private val quranViewModel: QuranViewModel by activityViewModels()
    private lateinit var bookmarkAdapter: BookmarkAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        loadBookmarks()
        observeBookmarks()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        bookmarkAdapter =
            BookmarkAdapter(listOf()) // Initialize with an empty list
        binding.recyclerView.adapter = bookmarkAdapter
    }

    private fun loadBookmarks() {
        quranViewModel.loadBookmarks(requireContext())
    }

    private fun observeBookmarks() {
        Log.d("BookmarkFragment", "observeBookmarks called")
        quranViewModel.bookmarkedVerses.observe(viewLifecycleOwner) { bookmarks ->
            Log.d("BookmarkFragment", "Updated bookmarks: ${bookmarks.size} items")
            binding.apply {
                if (bookmarks.isNotEmpty()) {
                    Log.d("BookmarkFragment", "Updated bookmarks: $bookmarks")
                    recyclerView.visibility = View.VISIBLE
                    ivNoBookmark.visibility = View.GONE
                    tvNoBookmark.visibility = View.GONE
                    bookmarkAdapter.updateBookmarks(bookmarks)
                } else {
                    recyclerView.visibility = View.GONE
                    ivNoBookmark.visibility = View.VISIBLE
                    tvNoBookmark.visibility = View.VISIBLE
                    Log.d("BookmarkFragment", "No bookmarks available")
                }
                bookmarkAdapter.updateBookmarks(bookmarks) // Always update with new bookmarks
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference to avoid memory leaks
    }
}
