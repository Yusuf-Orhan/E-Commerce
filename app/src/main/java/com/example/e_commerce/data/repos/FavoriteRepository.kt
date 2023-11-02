package com.example.e_commerce.data.repos

import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.local.FavoriteDao
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.FavoriteModel
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao : FavoriteDao
){
    suspend fun insertFavorite(productsItem: ProductsItem){
        val favorite = FavoriteModel(productsItem)
        dao.insertFavorite(favorite)
    }

}