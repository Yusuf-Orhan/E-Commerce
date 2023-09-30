package com.example.e_commerce.data.repos

import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.model.room.FavoriteModel
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.room.FavoriteDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao : FavoriteDao
) {
    val favoriteList = MutableLiveData<List<FavoriteModel>>()
    suspend fun addFavorite(favoriteModel: FavoriteModel){
        dao.insert(favoriteModel)
    }
    suspend fun deleteFavorite(item : FavoriteModel){
        dao.delete(item)
    }
    suspend fun getAllData() {
        favoriteList.value = dao.getAllFavoriteProducts()
    }
}