package com.example.e_commerce.di

import com.example.e_commerce.common.Constants.BASE_URL
import com.example.e_commerce.data.remote.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule  {

    @Singleton
    @Provides
    fun provideRetrofitApi() : RetrofitApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build().create(RetrofitApi::class.java)
}