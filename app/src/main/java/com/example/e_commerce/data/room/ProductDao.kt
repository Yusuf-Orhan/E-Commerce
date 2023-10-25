package com.example.e_commerce.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Query("UPDATE ProductModel SET isFavorite = :isFavorite WHERE id = :productId")
    suspend fun addFavorite(productId: Int, isFavorite: Boolean)
    @Query("SELECT isFavorite FROM ProductModel")
    suspend fun getFavorite() : List<Boolean>
    @Query("SELECT * FROM ProductModel")
    fun getAllCart() : Flow<List<ProductModel>>
    @Insert
    suspend fun insert(productModel: ProductModel)

    @Delete
    suspend fun delete(productModel: ProductModel)
}