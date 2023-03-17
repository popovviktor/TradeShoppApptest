package com.example.testcleanarch.adapter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testcleanarch.R
import com.example.testcleanarch.databinding.ItemFlashSaleForRvBinding
import com.example.testcleanarch.domain.models.FlashSaleItem

import com.squareup.picasso.Picasso


class AdapterFlashSale:RecyclerView.Adapter<AdapterFlashSale.FlashSaleHolder>() {
    private val flashSaleList =ArrayList<FlashSaleItem>()
    class FlashSaleHolder(item: View):RecyclerView.ViewHolder(item) {
    private val binding = ItemFlashSaleForRvBinding.bind(item)
        fun bind(item: FlashSaleItem) = with(binding){
        categoryItem.text = item.category
        priceItem.text = "$ " + item.price.toString()
        nameitem.text = item.name
        discount.text = item.discount.toString() +"% off"
        val cal = CallbackForPicassoSetBackGroundLayout(contrItem,imViewFlashitem)
            Picasso.get().load(item.imageUrl)
                .resize(174,200)
                .into(imViewFlashitem,cal)
            imViewFlashitem.visibility = View.INVISIBLE
            itemView.setOnClickListener {
                var bundle: Bundle = Bundle()
                bundle.putString("mainphoto",item.imageUrl)

                itemView.findNavController().navigate(R.id.itemTouchFragment,bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_flash_sale_for_rv,parent,false)
        return FlashSaleHolder(view)
    }

    override fun onBindViewHolder(holder: FlashSaleHolder, position: Int) {
        holder.bind(flashSaleList.get(position))
    }

    override fun getItemCount(): Int {
        return flashSaleList.size
    }
    fun add(flashSaleItem: FlashSaleItem){
        flashSaleList.add(flashSaleItem)
        notifyDataSetChanged()
    }
    fun addAll(flashSaleItems:ArrayList <FlashSaleItem>){
        flashSaleList.addAll(flashSaleItems)
        notifyDataSetChanged()
    }
    fun clear(){
        flashSaleList.clear()
        notifyDataSetChanged()
    }

}