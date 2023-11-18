package com.example.e_commerce.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.common.loadImage
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.FavoriteModel
import com.example.e_commerce.databinding.FavoriteListItemBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    val list = ArrayList<FavoriteModel>()
    class FavoriteViewHolder(val binding : FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(productsItem: ProductsItem,context: Context){
            with(binding){
                favoriteImage.loadImage(productsItem.image,context)
                descriptionText.text = productsItem.title
                priceText.text = "${productsItem.price}$"
                rateText.text = productsItem.rating.rate.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position].productsItem,holder.itemView.context)
    }
    fun loadData(newList : List<FavoriteModel>){
        list.addAll(newList)
    }
}