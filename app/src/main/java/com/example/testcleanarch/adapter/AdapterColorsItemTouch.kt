package com.example.testcleanarch.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.ColorsItemForRvBinding

class AdapterColorsItemTouch:RecyclerView.Adapter<AdapterColorsItemTouch.ColorsHolder>() {
    private val colorsList = ArrayList<String>()
    class ColorsHolder(item:View):RecyclerView.ViewHolder(item) {
    private val binding = ColorsItemForRvBinding.bind(item)
        fun bind(item:String)= with(binding){
            colorItemsItem.setBackgroundColor(Color.parseColor(item))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.colors_item_for_rv,parent,false)
        return ColorsHolder(view)
    }

    override fun onBindViewHolder(holder: ColorsHolder, position: Int) {
        holder.bind(colorsList.get(position))
    }

    override fun getItemCount(): Int {
        return colorsList.size
    }
    fun addAll(colors:ArrayList<String>){
        colorsList.addAll(colors)
        notifyDataSetChanged()
    }
    fun clear(){
        colorsList.clear()
        notifyDataSetChanged()
    }
}