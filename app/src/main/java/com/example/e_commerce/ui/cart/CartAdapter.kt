package com.example.e_commerce.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.common.loadImage
import com.example.e_commerce.data.model.local.ProductModel
import com.example.e_commerce.databinding.CartRwItemBinding


class CartAdapter(val context: Context) : RecyclerView.Adapter<CartAdapter.CartRwViewHolder>() {
    private val productList = arrayListOf<ProductModel>()
    var deleteClick: (Int) -> Unit = {}

    class CartRwViewHolder(val binding: CartRwItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(productModel: ProductModel, deleteClick: (Int) -> Unit = {}, context: Context) {
            with(binding) {
                titleText = productModel.title
                priceText = "$${productModel.price}"
                cartRwImage.loadImage(productModel.image, context = context)
                binding.deleteButton.setOnClickListener {
                    deleteClick(productModel.id)
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
            productList[position], deleteClick = { deleteClick(productList[position].id) }, context
        )
    }
    fun loadData(newList : List<ProductModel>){
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

}