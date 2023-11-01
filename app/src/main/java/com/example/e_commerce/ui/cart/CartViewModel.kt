package com.example.e_commerce.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.repos.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repo: CartRepository) : ViewModel() {
    private var _allProductList: MutableLiveData<List<ProductModel>>? =
        MutableLiveData<List<ProductModel>>()
    val allProductList: LiveData<List<ProductModel>> get() = repo.productList
    var totalBalance = MutableLiveData<Double>()

    init {
        totalBalance = repo.totalBalance
    }

    fun getAllProducts() = viewModelScope.launch {
        repo.getAllProducts()
    }

    fun getTotalBalance() = viewModelScope.launch {
        repo.getTotalBalance()
    }

    fun deleteItem(id: Int) = viewModelScope.launch {
        repo.deleteItem(id)
        getAllProducts()
        getTotalBalance()
    }


}