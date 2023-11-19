package com.example.e_commerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.e_commerce.common.Converters
import com.example.e_commerce.data.model.local.FavoriteModel
import com.example.e_commerce.data.model.local.ProductModel

@Database(entities = [ProductModel::class,FavoriteModel::class], version = 2)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao() : ProductDao
    abstract fun favoriteDao() : FavoriteDao
}