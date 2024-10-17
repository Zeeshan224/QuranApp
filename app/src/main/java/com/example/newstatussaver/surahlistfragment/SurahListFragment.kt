package com.example.newstatussaver.surahlistfragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstatussaver.bookmarkfragment.BookmarkFragment
import com.example.newstatussaver.QuranViewModel
import com.example.newstatussaver.R
import com.example.newstatussaver.data.Data
import com.example.newstatussaver.databinding.FragmentSurahListBinding
import com.example.newstatussaver.homefragment.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView

class SurahListFragment : Fragment() {

    private var _binding: FragmentSurahListBinding? = null
    private val binding get() = _binding!!
    private val quranViewModel: QuranViewModel by activityViewModels()
    private lateinit var adapter: SurahListAdapter
    private var originalSurahList: List<Data> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurahListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        observeViewModel()
        passingData()
        setUpRecyclerView()
        checkNetworkConnectivity()
        searchSurah()
    }

    private fun setUpRecyclerView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }
    }

    private fun searchSurah() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                val query = s.toString()
                filterSurahList(query)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun passingData() {
        adapter = SurahListAdapter(originalSurahList) { selectedSurah ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToCompSurahFragment(
                    selectedSurah
                )
            findNavController().navigate(action)
        }
    }

    private fun observeViewModel() {
        quranViewModel.fetchSurahList()
        quranViewModel.surahList.observe(viewLifecycleOwner) { surahList ->
            originalSurahList = surahList
            adapter.submitList(surahList)
        }
    }

    private fun checkNetworkConnectivity() {
        binding.apply {
            if (!isNetworkAvailable()) {
                // Show placeholder when offline and no data
                placeholderImage.visibility = View.VISIBLE // Make sure to set the correct view ID
                recyclerView.visibility = View.GONE
                quran.visibility = View.GONE
                search.visibility = View.GONE
            } else {
                placeholderImage.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                quran.visibility = View.VISIBLE
                search.visibility = View.VISIBLE
            }
        }
    }
    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val editText = binding.search
            if (editText.hasFocus()) {
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                editText.clearFocus()
            } else {
                isEnabled = false
                requireActivity().finishAffinity()
            }
        }
    }
    private fun filterSurahList(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalSurahList
        } else {
            originalSurahList.filter {
                it.name.long.contains(query, ignoreCase = true)
            }
        }
        adapter.submitList(filteredList)
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference to avoid memory leaks
    }
}
