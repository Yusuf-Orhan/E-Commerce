package com.example.e_commerce.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import com.google.android.gms.common.api.Response

@Dao
interface ProductDao {
    @Query("UPDATE ProductModel SET piece = :newPieceValue WHERE id = :productId")
    suspend fun updatePiece(productId: Int, newPieceValue: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM ProductModel WHERE id = :id)")
    suspend fun exists(id: Int): Boolean

    @Query("SELECT * FROM ProductModel")
    suspend fun getAllCart() : List<ProductModel>
    @Insert
    suspend fun insert(productModel: ProductModel)
    @Delete
    suspend fun delete(productModel: ProductModel)
}