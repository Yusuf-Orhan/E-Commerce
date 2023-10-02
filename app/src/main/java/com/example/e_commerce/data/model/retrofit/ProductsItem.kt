package com.example.e_commerce.data.model.retrofit

import androidx.room.Entity
import java.io.Serializable

data class ProductsItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Serializable