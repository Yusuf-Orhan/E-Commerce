package com.example.e_commerce.data.retrofit



import com.example.e_commerce.data.model.retrofit.Products
import retrofit2.Response

import retrofit2.http.GET

interface RetrofitApi {
    @GET("/products")
    suspend fun getProducts() : Response<Products>
}