package com.example.e_commerce.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.retrofit.Rating

@Entity
data class ProductModel (val category: String,
                         val description: String,
                         val id: Int,
                         val image: String,
                         val price: Double,
                         val rating: Double,
                         var piece : Int,
                         val title: String, ){
    @PrimaryKey(autoGenerate = true) var uid : Int = 0

}
