package com.example.newstatussaver.surahlistfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstatussaver.QuranViewModel
import com.example.newstatussaver.data.Data
import com.example.newstatussaver.databinding.FragmentDetailsBinding

class SurahListFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val quranViewModel: QuranViewModel by viewModels()
    private lateinit var adapter: SurahListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val surahList: List<Data> = getSurahList()

        adapter = SurahListAdapter(surahList){selectedSurah->
            val action = SurahListFragmentDirections.actionDetailsFragmentToCompSurahFragment(selectedSurah)
            findNavController().navigate(action)
        }
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }

        quranViewModel.fetchSurahList()

        quranViewModel.surahList.observe(viewLifecycleOwner){ surahList->
            adapter.submitList(surahList)
        }

    }

    private fun getSurahList():List<Data>{
        return listOf()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference to avoid memory leaks
    }
}