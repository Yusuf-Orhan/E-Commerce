package com.example.e_commerce.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerce.data.model.room.FavoriteModel

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAllFavorite() : List<FavoriteModel>
    @Query("SELECT * FROM favorite WHERE uid = :id")
    suspend fun getCurrentFavorite(id : Int) : FavoriteModel?
    @Insert
    suspend fun insertFavorite(favoriteModel: FavoriteModel)
    @Delete
    suspend fun deleteFavorite(favoriteModel: FavoriteModel)
}