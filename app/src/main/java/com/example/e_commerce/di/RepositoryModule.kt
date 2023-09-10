package com.example.e_commerce.di

import com.example.e_commerce.data.repos.CartRepository
import com.example.e_commerce.data.repos.MainRepository
import com.example.e_commerce.data.repos.ProfileRepository
import com.example.e_commerce.data.repos.UserRepository
import com.example.e_commerce.data.retrofit.RetrofitApi
import com.example.e_commerce.data.room.ProductDao
import com.example.e_commerce.data.room.ProductDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepo(firebaseAuth: FirebaseAuth,fireStore : FirebaseFirestore) : UserRepository = UserRepository(firebaseAuth,fireStore)

    @Provides
    @Singleton
    fun provideProfileRepo(firebaseAuth: FirebaseAuth,fireStore: FirebaseFirestore) : ProfileRepository = ProfileRepository(firebaseAuth,fireStore)
    @Provides
    @Singleton
    fun provideMainRepo(api : RetrofitApi) : MainRepository = MainRepository(api)

    @Provides
    @Singleton
    fun provideCartRepo(dao : ProductDao) : CartRepository = CartRepository(dao)
}