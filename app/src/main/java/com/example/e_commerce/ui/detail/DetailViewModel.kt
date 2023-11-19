package com.example.e_commerce.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.remote.ProductsItem
import com.example.e_commerce.data.repository.CartRepository
import com.example.e_commerce.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteRepo: FavoriteRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private var isFavorite = MutableLiveData<Boolean>()
    val state = MutableLiveData(DetailState(isFavorite = false))

    init {
        isFavorite = favoriteRepo.isFavorite
    }

    fun addFavorite(id: Int, productsItem: ProductsItem) = viewModelScope.launch {
        if (isFavorite.value == true) favoriteRepo.deleteFavorite(id)
        else favoriteRepo.insertFavorite(productsItem, id)
        state.value = state.value?.copy(isFavorite = isFavorite.value)
    }

    fun addCart(productItem: ProductsItem) = viewModelScope.launch {
        cartRepository.insertItem(productItem)
    }

    fun controlFavorite(id: Int) = viewModelScope.launch {
        favoriteRepo.controlFavorite(id)
        state.value = state.value?.copy(isFavorite = isFavorite.value)
    }

}


data class DetailState(
    val isFavorite: Boolean? = null
)
