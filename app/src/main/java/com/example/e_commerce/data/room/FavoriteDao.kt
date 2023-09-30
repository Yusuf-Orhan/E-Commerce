package com.example.e_commerce.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.e_commerce.data.model.room.FavoriteModel

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAllFavoriteProducts() : List<FavoriteModel>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : FavoriteModel)
    @Delete
    suspend fun delete(item : FavoriteModel)
}