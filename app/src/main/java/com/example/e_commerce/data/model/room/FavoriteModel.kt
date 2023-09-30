package com.example.e_commerce.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.e_commerce.common.Converters
import com.example.e_commerce.data.model.retrofit.ProductsItem

@Entity(tableName = "favorite")
data class FavoriteModel(

    @TypeConverters(Converters::class) val item : ProductsItem,

    val isFavorite : Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
