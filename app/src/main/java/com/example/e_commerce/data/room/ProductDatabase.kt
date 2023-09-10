package com.example.e_commerce.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_commerce.data.model.room.ProductModel

@Database(entities = [ProductModel::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao() : ProductDao
}