package com.example.e_commerce.ui.main

import android.text.method.MultiTapKeyListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.repository.CartRepository
import com.example.e_commerce.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo : MainRepository,private val cartRepository: CartRepository): ViewModel() {
    val favoriteModels = MutableLiveData<List<Boolean>>()
    private var _productsItemList = MutableLiveData<List<ProductsItem>>()
    val productsItemList : LiveData<List<ProductsItem>>
        get() = _productsItemList
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading
    private var _error = MutableLiveData<Boolean>()
    val error : LiveData<Boolean>
        get() = _error

    val state = MutableLiveData(MainState(isLoading = true))

    init {
        _productsItemList = repo.productsItemList
        _isLoading = repo.isLoading
        _error = repo.error
    }
    fun getData() = viewModelScope.launch{

    }

    fun controlIsFavorite() = viewModelScope.launch{
        favoriteModels.value = cartRepository.controlIsFavorite()
    }
}




data class MainState(
    val isLoading : Boolean? = null,
    val isError : Boolean? = null,
    val isSuccess : List<ProductsItem>? = emptyList()
)