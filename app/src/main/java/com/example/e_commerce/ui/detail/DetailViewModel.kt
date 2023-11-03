package com.example.e_commerce.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.repository.CartRepository
import com.example.e_commerce.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(private val favoriteRepo : FavoriteRepository,private val cartRepository: CartRepository) : ViewModel() {
    var  isFavorite = MutableLiveData<Boolean>()
    init {
        isFavorite = favoriteRepo.isFavorite
    }
    fun addFavorite(id : Int,productsItem: ProductsItem ) = viewModelScope.launch {
        favoriteRepo.insertFavorite(productsItem,id)
    }

    fun addCart(productItem: ProductsItem) = viewModelScope.launch{
        cartRepository.insertItem(productItem)
        controlFavorite(productItem.id)
    }
    fun controlFavorite(id : Int) = viewModelScope.launch{
        favoriteRepo.controlFavorite(id)
    }

}