package com.example.e_commerce.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_commerce.data.room.ProductDao
import com.example.e_commerce.data.room.ProductDatabase
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
    fun provideRoomDatabase(@ApplicationContext context : Context)  = Room.databaseBuilder(context,ProductDatabase::class.java,"Database").allowMainThreadQueries().build()
    @Provides
    @Singleton
    fun provideDao(database: ProductDatabase) : ProductDao = database.productDao()
}

