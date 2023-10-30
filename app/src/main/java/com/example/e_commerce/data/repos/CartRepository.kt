package com.example.e_commerce.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.local.ProductDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: ProductDao) {
    val totalBalance = MutableLiveData<Double>(0.0)
    val productList: LiveData<List<ProductModel>>
        get() {
            return dao.getAllCart()
        }

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

    suspend fun setFavorite(itemId: Int, isFavorite: Boolean) {
        dao.addFavorite(itemId, isFavorite)
    }

    fun getAllProducts() {
        dao.getAllCart()
    }

    fun getTotalBalance() {
        dao.getAllCart()?.value?.forEach {
            totalBalance.value = totalBalance.value?.plus(it.price)
        }
    }
}