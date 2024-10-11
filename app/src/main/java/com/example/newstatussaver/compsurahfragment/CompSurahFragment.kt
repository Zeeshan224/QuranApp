package com.example.newstatussaver.compsurahfragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstatussaver.QuranViewModel
import com.example.newstatussaver.data.Verse
import com.example.newstatussaver.databinding.FragmentCompSurahBinding
import kotlin.math.log

class CompSurahFragment : Fragment() {
    private var _binding: FragmentCompSurahBinding? = null
    private val binding get() = _binding!!
    private val quranViewModel: QuranViewModel by activityViewModels()
    private lateinit var surahDataAdapter: SurahDataAdapter
    private val args: CompSurahFragmentArgs by navArgs()
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var currentAudioUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }
    private fun observeViewModel() {
        val selectedSurah = args.selectedSurah
        quranViewModel.fetchSurah(selectedSurah)
        quranViewModel.surahData.observe(viewLifecycleOwner) { surahData ->
            surahData?.let {
                setUpRecyclerView(it.verses)
                it.verses.forEach { verse ->
                    Log.d("surah", "Verse: ${verse.text.arab}")
                }
            }
        }
        quranViewModel.bookmarkedVerses.observe(viewLifecycleOwner){ bookmarks->
            Log.d("Bookmark", "Observed bookmarked verses: $bookmarks")
        }
    }
    private fun setUpRecyclerView(verses: List<Verse>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        surahDataAdapter = SurahDataAdapter(verses,
            onPlayClickListener = { audioUri -> handlePlayClick(audioUri) },
            onBookmarkClick = { verse-> handleBookmarkClick(verse)},
            onShareClick = { verse-> handleShareClick(verse)}
        )
        binding.recyclerView.adapter = surahDataAdapter
    }
    private fun handleShareClick(verse: Verse) {
        val verseText = verse.text.arab // Or however you want to format the verse text
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, verseText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share Verse"))
    }
    private fun handlePlayClick(audioUri: String) {
        Log.d("AudioPlay", "Play icon clicked for URI: $audioUri")

        if (isPlaying && currentAudioUrl != audioUri) {
            Log.d("AudioPlay", "Stopping current audio.")
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            isPlaying = false
        }

        if (currentAudioUrl != audioUri) {
            Log.d("AudioPlay", "Starting new audio: $currentAudioUrl")
            currentAudioUrl = audioUri
            mediaPlayer = MediaPlayer().apply {
                setDataSource(audioUri)
                setOnPreparedListener {
                    it.start()
                    this@CompSurahFragment.isPlaying = true
                    Log.d("AudioPlay", "Audio started playing.")
                }
                setOnCompletionListener {
                    this@CompSurahFragment.isPlaying = false
                    Log.d("AudioPlay", "Audio playback completed.")
                }
                prepareAsync()
            }
        }
        else {
            Log.d("AudioPlay", "Pausing audio.")
            mediaPlayer?.pause()
            isPlaying = false
        }
    }

    private fun handleBookmarkClick(verse: Verse) {
        Toast.makeText(requireContext(), "bookmark saved", Toast.LENGTH_SHORT).show()
        Log.d("Bookmark", "Bookmark clicked for verse: ${verse.text.arab}")
        quranViewModel.addBookmark(verse)
        quranViewModel.bookmarkedVerses.observe(viewLifecycleOwner){ bookmarks->
            Log.d("Bookmark","Current bookmarked verses in ViewModel: $bookmarks")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference to avoid memory leaks

        mediaPlayer?.release()
        mediaPlayer = null
    }
}