package com.example.e_commerce.data.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.retrofit.RetrofitApi
import com.example.e_commerce.data.model.firebase.User

import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response


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