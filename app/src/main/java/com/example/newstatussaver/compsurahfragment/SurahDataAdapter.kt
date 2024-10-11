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
    private val onBookmarkClick: (Verse) -> Unit,
    private val onShareClick: (Verse) -> Unit
) :
    RecyclerView.Adapter<SurahDataAdapter.SurahDataViewHolder>() {
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
        val arabicTextSpannable = SpannableString(verse.text.arab)
        Log.d("color", "onBindViewHolder: $arabicTextSpannable")

// Apply color to the entire text
        arabicTextSpannable.setSpan(
            ForegroundColorSpan(Color.WHITE), 0, arabicTextSpannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        holder.binding.apply {
            arabicText.text = verse.text.arab
            englishText.text = verse.translation.en
            verseNumberTextView.text = verse.number.inSurah.toString()
            if (holder.isPlaying) {
                icPlay.setImageResource(R.drawable.bookmark)
            }
            else{
                icPlay.setImageResource(R.drawable.play)
            }
            if (position == 0) {
                cover.visibility = View.VISIBLE
            } else {
                cover.visibility = View.GONE
            }

            icPlay.setOnClickListener {
                holder.isPlaying = !holder.isPlaying
                if (holder.isPlaying){
                    icPlay.setImageResource(R.drawable.bookmark)
                    onPlayClickListener(verse.audio.primary)
                }
                else{
                    icPlay.setImageResource(R.drawable.play)
                }
            }
            bookmark.setOnClickListener {
                onBookmarkClick(verse)
            }
            share.setOnClickListener {
                onShareClick(verse)
            }
        }
    }
}
