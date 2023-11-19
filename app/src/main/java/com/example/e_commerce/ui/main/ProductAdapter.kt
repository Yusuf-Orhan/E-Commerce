package com.example.e_commerce.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.common.loadImage
import com.example.e_commerce.data.model.remote.ProductsItem
import com.example.e_commerce.databinding.ProductListItemBinding

class ProductAdapter(val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val productList = ArrayList<ProductsItem>()
    var isChecked = false
    var onItemClick: (ProductsItem) -> Unit = {}

    class ProductViewHolder(val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            onClick: (ProductsItem) -> Unit = {},
            productsItem: ProductsItem,
            context: Context
        ) {
            with(binding) {
                productItemImage.loadImage(url = productsItem.image, context)
                priceText = "${productsItem.price}$"
                titleText = productsItem.title
                rootView.setOnClickListener {
                    onClick(productsItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = DataBindingUtil.inflate<ProductListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.product_list_item,
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(onItemClick, productList[position], context = context)
    }

    fun loadData(newList: List<ProductsItem>) {
        productList.clear()
        productList.addAll(newList)
        println(productList.size)
    }
}