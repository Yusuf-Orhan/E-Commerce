package com.example.e_commerce.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerce.data.model.remote.ProductsItem

@Entity(tableName = "favorite")
data class FavoriteModel(
    val uid : Int,
    val productsItem: ProductsItem
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}