package com.example.e_commerce.ui.main

import android.provider.LiveFolders
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.Products
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.repos.CartRepository
import com.example.e_commerce.data.repos.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    init {
        _productsItemList = repo.productsItemList
        _isLoading = repo.isLoading
        _error = repo.error
    }
    fun getData() = viewModelScope.launch{
        repo.getData()
    }
    fun setFavorite(itemId : Int,isFavorite : Boolean) = viewModelScope.launch {
        cartRepository.setFavorite(itemId,isFavorite)
    }
    fun controlIsFavorite() = viewModelScope.launch{
        favoriteModels.value = cartRepository.controlIsFavorite()
    }
}