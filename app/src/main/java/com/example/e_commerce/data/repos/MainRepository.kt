package com.example.e_commerce.data.repos

import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.remote.RetrofitApi

import javax.inject.Inject


class MainRepository @Inject constructor(private val api: RetrofitApi) {
    val productsItemList = MutableLiveData<List<ProductsItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    suspend fun getData() {
        isLoading.value = true
        runCatching {
            api.getProducts()
        }.onSuccess { response ->
            val isSuccessful = response.isSuccessful
            productsItemList.value = response.body().orEmpty()
            error.value = isSuccessful.not()
            isLoading.value = false
        }.onFailure {
            error.value = true
            isLoading.value = false
        }
    }
}