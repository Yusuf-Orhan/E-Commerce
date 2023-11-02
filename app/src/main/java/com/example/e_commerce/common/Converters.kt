package com.example.e_commerce.common

import androidx.room.TypeConverter
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.google.gson.Gson

class Converters {
    private val gson = Gson()
    @TypeConverter
    fun fromProductsItem(item: ProductsItem): String {
        return gson.toJson(item)
    }

    @TypeConverter
    fun toProductsItem(itemString: String): ProductsItem {
        return gson.fromJson(itemString, ProductsItem::class.java)
    }
}