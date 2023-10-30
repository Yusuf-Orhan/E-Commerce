package com.example.e_commerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.e_commerce.common.Converters
import com.example.e_commerce.data.model.room.ProductModel

@Database(entities = [ProductModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao() : ProductDao
}