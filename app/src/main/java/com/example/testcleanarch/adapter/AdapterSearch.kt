package com.example.testcleanarch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.ItemForSearchRvBinding

class AdapterSearch:RecyclerView.Adapter<AdapterSearch.SearchHolder>() {
    private var searchword:String? = null
    private val wordsList = ArrayList<String>()
    private var searchwordsList = ArrayList<String>()
    class SearchHolder(item: View):RecyclerView.ViewHolder(item) {
        private val binding = ItemForSearchRvBinding.bind(item)
        fun bind(item: String) = with(binding){
            binding.searchresult.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_search_rv,parent,false)
        return SearchHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(searchwordsList.get(position))
    }

    override fun getItemCount(): Int {
        return searchwordsList.size
    }
    fun addAllWords(words:ArrayList<String>){
        wordsList.addAll(words)
        notifyDataSetChanged()
    }
    fun clear(){
        wordsList.clear()
        notifyDataSetChanged()
    }
    fun addSearchWord(word:String){
        searchword = word
        notifyDataSetChanged()
    }
    fun searchWords(){
        var arrwords = ArrayList<String>()
        searchword
        for (elem in wordsList){
            if (elem.indexOf(searchword.toString())!=-1){
                arrwords.add(elem)
            }
        }
        if (arrwords == null || arrwords.size ==0){
            arrwords.add("Unfortunately, nothing was found.")
        }
        searchwordsList = arrwords
        notifyDataSetChanged()
    }
}