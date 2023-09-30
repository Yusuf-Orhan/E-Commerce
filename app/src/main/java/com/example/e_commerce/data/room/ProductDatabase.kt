package com.example.e_commerce.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.e_commerce.common.Converters
import com.example.e_commerce.data.model.room.FavoriteModel
import com.example.e_commerce.data.model.room.ProductModel

@Database(entities = [ProductModel::class,FavoriteModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao() : ProductDao
    abstract fun favoriteDao() : FavoriteDao
}