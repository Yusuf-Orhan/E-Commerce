package com.example.e_commerce.data.remote



import com.example.e_commerce.data.model.remote.Products


import retrofit2.http.GET

interface RetrofitApi {
    @GET("/products")
    suspend fun getProducts() : Products

}