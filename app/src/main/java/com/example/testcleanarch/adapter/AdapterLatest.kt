package com.example.testcleanarch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.ItemLatestForRvBinding

import com.example.testcleanarch.domain.models.LatestItem
import com.squareup.picasso.Picasso

class AdapterLatest:RecyclerView.Adapter<AdapterLatest.LatestHolder>() {
    private val latestList =ArrayList<LatestItem>()
    class LatestHolder(item:View):RecyclerView.ViewHolder(item) {
    private val binding = ItemLatestForRvBinding.bind(item)
        fun bind(item: LatestItem)= with(binding){
            categoryItemLatest.text = item.category
            nameitemLatest.text = item.name
            priceItemLatest.text = "$ " + item.price.toString()
            val cal = CallbackForPicassoSetBackGroundLayout(contrItemLatest,imViewLatestitem)
            Picasso.get().load(item.imageUrl)
                .resize(125,170)
                .into(imViewLatestitem,cal)
            imViewLatestitem.visibility = View.INVISIBLE
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_latest_for_rv,parent,false)
        return LatestHolder(view)
    }

    override fun onBindViewHolder(holder: LatestHolder, position: Int) {
        holder.bind(latestList.get(position))
    }

    override fun getItemCount(): Int {
        return latestList.size
    }
    fun add(item: LatestItem){
        latestList.add(item)
        notifyDataSetChanged()
    }
    fun addAll(latestitems:ArrayList<LatestItem>){
        latestList.addAll(latestitems)
        notifyDataSetChanged()
    }
    fun clear(){
        latestList.clear()
        notifyDataSetChanged()
    }
}

