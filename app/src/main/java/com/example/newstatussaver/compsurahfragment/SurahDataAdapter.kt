package com.example.newstatussaver.compsurahfragment

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newstatussaver.R
import com.example.newstatussaver.data.Verse
import com.example.newstatussaver.databinding.SurahViewBinding

class SurahDataAdapter(
    private val surahInfo: List<Verse>,
    private val onPlayClickListener: (String) -> Unit,
    private val onPauseClickListener: () -> Unit,
    private val onBookmarkClick: (Verse) -> Unit,
    private val onShareClick: (Verse) -> Unit
) : RecyclerView.Adapter<SurahDataAdapter.SurahDataViewHolder>() {

    var currentlyPlayingPosition: Int = -1

    inner class SurahDataViewHolder(val binding: SurahViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isPlaying: Boolean = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahDataViewHolder {
        return SurahDataViewHolder(
            SurahViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = surahInfo.size
    override fun onBindViewHolder(holder: SurahDataViewHolder, position: Int) {
        val verse = surahInfo[position]

        holder.binding.apply {
            arabicText.text = verse.text.arab
            englishText.text = verse.translation.en
            verseNumberTextView.text = verse.number.inSurah.toString()
            if (currentlyPlayingPosition == position) {
                icPlay.setImageResource(R.drawable.pause)
            } else {
                icPlay.setImageResource(R.drawable.play)
            }

            if (verse.isBookmarked) {
                bookmark.setImageResource(R.drawable.baseline_bookmark_24)  // Filled bookmark icon
            } else {
                bookmark.setImageResource(R.drawable.group)  // Outlined bookmark icon
            }

            if (position == 0) {
                cover.visibility = View.VISIBLE
            } else {
                cover.visibility = View.GONE
            }

            icPlay.setOnClickListener {
                holder.isPlaying = !holder.isPlaying
                if (currentlyPlayingPosition == position) {
                    // Pause the current audio
                    icPlay.setImageResource(R.drawable.play)
                    onPauseClickListener()
                    currentlyPlayingPosition = -1
                } else {
                    // Play new audio
                    val previousPosition = currentlyPlayingPosition
                    currentlyPlayingPosition = position
                    icPlay.setImageResource(R.drawable.pause)

                    onPlayClickListener(verse.audio.primary)

                    // Reset the icon of the previously playing item
                    if (previousPosition != -1) {
                        notifyItemChanged(previousPosition)
                    }
                }
            }
            bookmark.setOnClickListener {
                verse.isBookmarked = !verse.isBookmarked

                // Update the bookmark icon based on the new state
                if (verse.isBookmarked) {
                    bookmark.setImageResource(R.drawable.baseline_bookmark_24)  // Filled icon
                } else {
                    bookmark.setImageResource(R.drawable.group)  // Outline icon
                }
                onBookmarkClick(verse)
            }
            share.setOnClickListener {
                onShareClick(verse)
            }
        }
    }
}
