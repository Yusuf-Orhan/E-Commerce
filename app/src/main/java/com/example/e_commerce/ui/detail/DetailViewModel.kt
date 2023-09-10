package com.example.e_commerce.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.repos.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(private val repo : CartRepository) : ViewModel() {
    val exist = MutableLiveData<Boolean?>()
    fun insertItem(productsItem: ProductsItem,piece : Int) = viewModelScope.launch {
        repo.insertItem(productsItem,piece)
    }
    fun control (uid : Int) = viewModelScope.launch {
        exist.value = repo.controlProduct(uid)
    }
    fun updatePiece(id : Int,newPiece : Int) = viewModelScope.launch {
        repo.updatePiece(id,newPiece)
    }
}