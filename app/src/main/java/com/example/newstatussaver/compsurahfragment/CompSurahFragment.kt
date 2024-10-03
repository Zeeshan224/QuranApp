package com.example.newstatussaver.compsurahfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.newstatussaver.R
import com.example.newstatussaver.databinding.FragmentCompSurahBinding

class CompSurahFragment : Fragment() {
    private var _binding:FragmentCompSurahBinding? = null
    private val binding get() = _binding!!

    private val args: CompSurahFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedSurah = args.selectedSurah

//        val args: CompSurahFragmentArgs by navArgs()
//        val surah = args.surah

        binding.surahName.text = selectedSurah.name.long
        binding.surahTranslation.text = selectedSurah.name.translation.en
        binding.surahRevelation.text = selectedSurah.revelation.en
        // Inflate the layout for this fragment
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference to avoid memory leaks
    }
}
