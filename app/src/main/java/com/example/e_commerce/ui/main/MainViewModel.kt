package com.example.e_commerce.ui.main

import android.text.method.MultiTapKeyListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.common.Resource
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.repository.CartRepository
import com.example.e_commerce.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val favoriteModels = MutableLiveData<List<Boolean>>()

    val state = MutableLiveData(MainState(isLoading = true))

    init {
        getData()
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.IsRefreshed -> {
                println("Is Refreshed")
                state.value = state.value?.copy(isLoading = true)
                getData()
            }

            is MainEvent.TryAgain -> {
                println("Is Products Clicked")
                state.value = state.value?.copy(isLoading = true)
                getData()
            }
        }
    }

    fun getData() = viewModelScope.launch {
        when (val result = repo.getData()) {
            is Resource.Success -> {
                state.value =
                    state.value?.copy(isLoading = false, isError = null, products = result.data)
            }

            is Resource.Error -> {
                state.value =
                    state.value?.copy(isLoading = false, isError = result.msg, products = null)
            }
        }
    }

    fun controlIsFavorite() = viewModelScope.launch {
        favoriteModels.value = cartRepository.controlIsFavorite()
    }
}

sealed class MainEvent {
    object IsRefreshed : MainEvent()
    object TryAgain : MainEvent()
}

data class MainState(
    val isLoading: Boolean? = null,
    val isError: String? = null,
    val products: List<ProductsItem>? = emptyList()
)