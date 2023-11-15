package com.example.e_commerce.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.local.FavoriteDao
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.FavoriteModel
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: FavoriteDao
) {
    val isFavorite = MutableLiveData<Boolean>()
    suspend fun insertFavorite(productsItem: ProductsItem, id: Int) {
        val favorite = FavoriteModel(id, productsItem)
        dao.insertFavorite(favorite)
    }

    suspend fun controlFavorite(id: Int) {
        val productsItem = dao.getCurrentFavorite(id)
        isFavorite.value = productsItem != null
        println("Favorite Repository Control : ${isFavorite.value}")
    }

    suspend fun deleteFavorite(id: Int) = dao.delete(id)

}