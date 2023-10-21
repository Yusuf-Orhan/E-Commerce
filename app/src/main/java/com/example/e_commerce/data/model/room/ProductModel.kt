package com.example.e_commerce.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductModel (
    val category: String,
    val description: String,
    val id: Int,
    val isFavorite : Boolean = false,
    val image: String,
    val price: Double,
    val rating: Double,
    var piece : Int = 0,
    val title: String) {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

}
