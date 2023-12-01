package com.example.e_commerce.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.common.Resource
import com.example.e_commerce.data.model.remote.ProductsItem
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
                state.value = state.value?.copy(isLoading = true)
                getData()
            }

            is MainEvent.TryAgain -> {
                state.value = state.value?.copy(isLoading = true)
                getData()
            }
        }
    }

    private fun getData() = viewModelScope.launch {
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