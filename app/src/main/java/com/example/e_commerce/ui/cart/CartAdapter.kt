package com.example.e_commerce.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.common.loadImage
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.databinding.CartRwItemBinding


class CartAdapter(val context: Context) : RecyclerView.Adapter<CartAdapter.CartRwViewHolder>() {
    var productList = listOf<ProductModel>()
    var deleteClick: (ProductModel) -> Unit = {}

    class CartRwViewHolder(val binding: CartRwItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(productModel: ProductModel, deleteClick: (ProductModel) -> Unit = {}, context: Context) {
            with(binding) {
                titleText = productModel.title
                priceText = "$${productModel.price}"
                cartRwImage.loadImage(productModel.image, context = context)
                binding.deleteButton.setOnClickListener {
                    deleteClick(productModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRwViewHolder {
        val binding = DataBindingUtil.inflate<CartRwItemBinding>(
            LayoutInflater.from(parent.context), R.layout.cart_rw_item, parent, false
        )
        return CartRwViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: CartRwViewHolder, position: Int) {
        holder.bind(
            productList[position], deleteClick = { deleteClick(productList[position]) }, context
        )
    }
    fun loadData(newList : List<ProductModel>){
        productList = newList
        notifyDataSetChanged()
    }

}