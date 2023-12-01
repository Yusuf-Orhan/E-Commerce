package com.example.e_commerce.di

import com.example.e_commerce.data.local.FavoriteDao
import com.example.e_commerce.data.repository.CartRepository

import com.example.e_commerce.data.repository.MainRepository
import com.example.e_commerce.data.repository.ProfileRepository
import com.example.e_commerce.data.repository.UserRepository
import com.example.e_commerce.data.remote.ProductsApi
import com.example.e_commerce.data.local.ProductDao
import com.example.e_commerce.data.repository.FavoriteRepository
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
    fun provideMainRepo(api : ProductsApi) : MainRepository = MainRepository(api)

    @Provides
    @Singleton
    fun provideCartRepo(dao : ProductDao) : CartRepository = CartRepository(dao)
    @Provides
    @Singleton
    fun provideFavoriteRepo(dao : FavoriteDao) : FavoriteRepository = FavoriteRepository(dao)

}