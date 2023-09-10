package com.example.e_commerce.data.repos

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.model.retrofit.Products
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.retrofit.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response


class MainRepository @Inject constructor(private val api : RetrofitApi){
    val productsItemList = MutableLiveData<ArrayList<ProductsItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    suspend fun getData(){
        isLoading.value = true
        val response = api.getProducts()
        if (response.isSuccessful) {
            isLoading.value = false
            error.value = false
            productsItemList.value = response.body()
        }else{
            isLoading.value = false
            error.value = true
        }

    }
}