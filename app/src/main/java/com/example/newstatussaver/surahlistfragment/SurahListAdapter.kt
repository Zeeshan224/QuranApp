package com.example.newstatussaver.surahlistfragment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newstatussaver.data.Data
import com.example.newstatussaver.databinding.ItemSurahBinding

class SurahListAdapter(private var surahList: List<Data>,
 private val onItemClick: (Data) -> Unit
 ) : RecyclerView.Adapter<SurahListAdapter.SurahViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ItemSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahList[position]
        holder.bind(surah)
        holder.itemView.setOnClickListener {
            onItemClick(surah)
        }
    }

    override fun getItemCount(): Int = surahList.size


    fun submitList(list: List<Data>) {
        this.surahList = list
        notifyDataSetChanged() // Notify the adapter of the data change
    }

    inner class SurahViewHolder(private val binding: ItemSurahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(surah: Data) {
            binding.surahNameTextView.text = surah.name.long
            binding.surahNumberTextView.text = "${surah.number}"
        }
    }
}

//class SurahListAdapter(private val onSurahClick: (Data) -> Unit) : RecyclerView.Adapter<SurahListAdapter.SurahViewHolder>(){
//
//    private var surahList : List<Data> = emptyList()
//
//    fun submitList(list: List<Data>){
//        surahList = list
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): SurahViewHolder {
//       val binding = ItemSurahBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return SurahViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
//        val surah = surahList[position]
//        holder.bind(surah)
//        holder.itemView.setOnClickListener { onSurahClick(surah)}
//    }
//
//    override fun getItemCount(): Int = surahList.size
//
//    inner class SurahViewHolder(private val binding: ItemSurahBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(surah: Data){
//            binding.surahNameTextView.text = surah.name.toString()
//        }
//    }
//}
