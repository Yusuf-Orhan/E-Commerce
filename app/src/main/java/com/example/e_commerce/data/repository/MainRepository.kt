package com.example.e_commerce.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.common.Resource
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.remote.RetrofitApi

import javax.inject.Inject


class MainRepository @Inject constructor(private val api: RetrofitApi) {
    val productsItemList = MutableLiveData<List<ProductsItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    suspend fun getData() : Resource<List<ProductsItem>> =
        try {
            Resource.Success(api.getProducts())
        }catch (e : Exception){
            Resource.Error(e.message ?: "")
        }

}