package com.example.e_commerce.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.repos.CartRepository
import com.example.e_commerce.data.room.ProductDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repo : CartRepository): ViewModel(){
    var allProductList = MutableLiveData<List<ProductModel>>()
    init {
        allProductList = repo.allProductList
    }
    fun getAllProducts() = viewModelScope.launch{
        repo.getAllProducts()
    }
    fun updatePiece(id : Int,newPiece : Int) = viewModelScope.launch {
        repo.updatePiece(id,newPiece)
    }
}