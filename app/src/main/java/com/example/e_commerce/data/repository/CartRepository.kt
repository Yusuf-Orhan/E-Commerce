package com.example.e_commerce.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.model.remote.ProductsItem
import com.example.e_commerce.data.model.local.ProductModel
import com.example.e_commerce.data.local.ProductDao
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: ProductDao) {
    val totalBalance = MutableLiveData<Double>(0.0)
    lateinit var productList: LiveData<List<ProductModel>>


    suspend fun insertItem(productsItem: ProductsItem) {
        dao.insert(
            productModel = ProductModel(
                productsItem.category,
                productsItem.description,
                productsItem.id,
                false,
                productsItem.image,
                productsItem.price,
                productsItem.rating.rate,
                productsItem.title
            )
        )
    }

    suspend fun deleteItem(id: Int) {
        dao.delete(id)
    }

    suspend fun controlIsFavorite(): List<Boolean> {
        return dao.getFavorite()
    }

    suspend fun getAllProducts() {
        productList = dao.getAllCart()
    }

    suspend fun getTotalBalance() {
        totalBalance.value = dao.getTotalAmount().sum()
    }
}