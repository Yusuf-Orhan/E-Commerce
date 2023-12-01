package com.example.e_commerce.data.repository

import com.example.e_commerce.common.Resource
import com.example.e_commerce.data.model.remote.ProductsItem
import com.example.e_commerce.data.remote.ProductsApi

import javax.inject.Inject


class MainRepository @Inject constructor(private val api: ProductsApi) {
    suspend fun getData() : Resource<List<ProductsItem>> =
        try {
            Resource.Success(api.getProducts())
        }catch (e : Exception){
            Resource.Error(e.message ?: "")
        }

}