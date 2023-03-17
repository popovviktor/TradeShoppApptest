package com.example.testcleanarch.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.size
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.PhotosItemTouchBinding
import com.example.testcleanarch.domain.models.ItemOnTouch
import com.example.testcleanarch.presentation.MainViewModel
import com.squareup.picasso.Picasso

class AdapterPhotosItemTouch:RecyclerView.Adapter<AdapterPhotosItemTouch.PhotosHolder>() {
    private val photoesList = ArrayList<String>()

    class PhotosHolder(item:View): RecyclerView.ViewHolder(item) {
    private val binding = PhotosItemTouchBinding.bind(item)
        fun bind(item: String)= with(binding){
            val cal = CallbackForPicassoSetBackGroundLayout(binding.constrItemPhotos,binding.photoItemTouchForRv)
            Picasso.get().load(item)
                .into(binding.photoItemTouchForRv)
            itemView.setOnClickListener {

                var bundle: Bundle = Bundle()
                bundle.putString("mainphoto",item)
               itemView.findNavController().navigate(R.id.itemTouchFragment,bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_item_touch,parent,false)
        return PhotosHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
        holder.bind(photoesList.get(position))
    }

    override fun getItemCount(): Int {
        return photoesList.size
    }
    fun add(photoes:ItemOnTouch){
        photoesList.addAll(photoes.imageUrls)
        notifyDataSetChanged()
    }
    fun addAll(photoes:ArrayList<String>){
        photoesList.addAll(photoes)
        notifyDataSetChanged()
    }
    fun clear(){
        photoesList.clear()
        notifyDataSetChanged()
    }
}