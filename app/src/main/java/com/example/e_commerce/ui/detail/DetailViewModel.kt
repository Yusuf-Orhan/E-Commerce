package com.example.e_commerce.ui.detail

import androidx.lifecycle.LiveData
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
    private var _isexist = MutableLiveData<Boolean>()
    val isexist : LiveData<Boolean> get() = _isexist
    init {
        _isexist = repo.isExists
    }
    fun insertItem(productsItem: ProductsItem,piece : Int ) = viewModelScope.launch {
        repo.insertItem(productsItem,piece)
    }
    fun updatePiece(id : Int,newPiece : Int) = viewModelScope.launch {
        repo.updatePiece(id,newPiece)
    }
}