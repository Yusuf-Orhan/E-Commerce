package com.example.e_commerce.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.e_commerce.common.Converters
import com.example.e_commerce.data.model.retrofit.ProductsItem

@Entity(tableName = "favorite")
data class FavoriteModel(
    val productsItem: ProductsItem
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}