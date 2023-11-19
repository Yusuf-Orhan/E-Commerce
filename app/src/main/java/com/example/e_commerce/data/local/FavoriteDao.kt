package com.example.e_commerce.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.e_commerce.data.model.local.FavoriteModel

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAllFavorite() : List<FavoriteModel>
    @Query("SELECT * FROM favorite WHERE uid = :id")
    fun getCurrentFavorite(id : Int) : FavoriteModel?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteModel: FavoriteModel)
    @Query("DELETE FROM favorite WHERE uid = :id")
    suspend fun delete(id: Int)
}