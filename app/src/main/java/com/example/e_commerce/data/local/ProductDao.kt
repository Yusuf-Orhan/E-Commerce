package com.example.e_commerce.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {


    @Query("SELECT price FROM products")
    suspend fun getTotalAmount(): List<Double>

    @Query("SELECT isFavorite FROM products")
    suspend fun getFavorite(): List<Boolean>

    @Query("SELECT * FROM products")
    fun getAllCart(): LiveData<List<ProductModel>>

    @Insert
    suspend fun insert(productModel: ProductModel)

    @Query("DELETE FROM products WHERE id = :uid")
    suspend fun delete(uid: Int)
}