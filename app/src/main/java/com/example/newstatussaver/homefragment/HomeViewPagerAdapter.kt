package com.example.newstatussaver.homefragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newstatussaver.bookmarkfragment.BookmarkFragment
import com.example.newstatussaver.surahlistfragment.SurahListFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        // Number of fragments (e.g., two fragments: SurahListFragment and BookmarkFragment)
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        // Return the appropriate fragment based on the position
        return when (position) {
            0 -> SurahListFragment() // First fragment (Surah List)
            1 -> BookmarkFragment() // Second fragment (Bookmarks)
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
