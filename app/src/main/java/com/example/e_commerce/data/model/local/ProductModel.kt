package com.example.e_commerce.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
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
