package com.example.e_commerce.data.remote



import com.example.e_commerce.data.model.retrofit.Products
import retrofit2.Response
import retrofit2.http.Body


import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @GET("/products")
    suspend fun getProducts() : Products

}