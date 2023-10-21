package com.example.e_commerce.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.room.ProductDao
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: ProductDao) {
    val allProductList = MutableLiveData<List<ProductModel>>()
    val totalBalance = MutableLiveData<Double>(0.0)

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
    suspend fun deleteItem(productModel: ProductModel){
        dao.delete(productModel)
    }
    suspend fun controlIsFavorite() : List<Boolean>{
        return dao.getFavorite()
    }
    suspend fun setFavorite(itemId : Int,isFavorite : Boolean){
        dao.addFavorite(itemId,isFavorite)
    }
    suspend fun getAllProducts() {
        dao.getAllCart().also {
            allProductList.value = it
        }
    }
    suspend fun getTotalBalance() {
        dao.getAllCart().forEach {
            totalBalance.value = totalBalance.value?.plus(it.price)
        }
    }
}