package com.example.e_commerce.di

import android.content.Context
import androidx.room.Room
import com.example.e_commerce.data.local.FavoriteDao
import com.example.e_commerce.data.local.ProductDao
import com.example.e_commerce.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context : Context)  = Room.databaseBuilder(context,
        ProductDatabase::class.java,"Database").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    @Provides
    @Singleton
    fun provideProductDao(database: ProductDatabase) : ProductDao = database.productDao()
    @Provides
    @Singleton
    fun provideFavoriteDao(database: ProductDatabase) : FavoriteDao = database.favoriteDao()
}

