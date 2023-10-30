package com.example.e_commerce.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductModel (
    val category: String,
    val description: String,
    val uid : Int,
    val isFavorite : Boolean = false,
    val image: String,
    val price: Double,
    val rating: Double,
    val title: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
