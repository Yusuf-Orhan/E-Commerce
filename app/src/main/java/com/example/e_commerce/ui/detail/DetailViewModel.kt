package com.example.e_commerce.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.repos.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(private val repo : CartRepository) : ViewModel() {
    fun insertItem(productsItem: ProductsItem ) = viewModelScope.launch {
        repo.insertItem(productsItem)
    }
}