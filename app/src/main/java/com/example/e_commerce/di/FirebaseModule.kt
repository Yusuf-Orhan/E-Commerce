package com.example.e_commerce.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  FirebaseModule {
    @Provides
    @Singleton
    fun firebaseAuthProvide() : FirebaseAuth {
        println("Firebase Auth Module Çalıltı")
       return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun firebaseDatabaseProvide() : FirebaseFirestore = FirebaseFirestore.getInstance()
}
