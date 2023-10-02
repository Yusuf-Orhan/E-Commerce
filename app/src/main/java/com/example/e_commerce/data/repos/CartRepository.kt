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
    val totalBalance = MutableLiveData<Double>()
    val isExists = MutableLiveData<Boolean>()

    suspend fun insertItem(productsItem: ProductsItem, piece: Int) {
        dao.insert(
            productModel = ProductModel(
                productsItem.category,
                productsItem.description,
                productsItem.id,
                false,
                productsItem.image,
                productsItem.price,
                productsItem.rating.rate,
                piece,
                productsItem.title
            )
        )
    }
    suspend fun controlIsFavorite() : List<Boolean>{
        return dao.getFavorite()
    }
    suspend fun setFavorite(itemId : Int,isFavorite : Boolean){
        dao.addFavorite(itemId,isFavorite)
    }
    suspend fun getData(): List<ProductModel> {
        return dao.getAllCart()
    }

    suspend fun getAllProducts() {
        dao.getAllCart().also {
            allProductList.value = it
        }
    }

    suspend fun updatePiece(id: Int, newPiece: Int) {
        dao.updatePiece(id, newPiece)
    }
    suspend fun getTotalBalance() {
        dao.getAllCart().forEach {
            totalBalance.value?.plus(it.price)
            println("(Repos) Total balance : ${totalBalance.value}")
        }
    }

}