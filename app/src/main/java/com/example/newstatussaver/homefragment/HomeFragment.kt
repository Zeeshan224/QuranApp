package com.example.newstatussaver.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.newstatussaver.R
import com.example.newstatussaver.bookmarkfragment.BookmarkFragment
import com.example.newstatussaver.databinding.FragmentHomeBinding
import com.example.newstatussaver.surahlistfragment.SurahListFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        viewPager.adapter = viewPagerAdapter

        // Set up TabLayout with ViewPager2
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Surah List" // Label for first tab
                1 -> "Bookmarks"  // Label for second tab
                else -> null
            }
        }.attach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

