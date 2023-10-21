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
    var _isexist = MutableLiveData<Boolean>()

    val item = MutableLiveData<ProductModel>()
    fun insertItem(productsItem: ProductsItem,piece : Int ) = viewModelScope.launch {
        repo.insertItem(productsItem,piece)
    }
    fun updatePiece(id : Int,newPiece : Int) = viewModelScope.launch {
        repo.updatePiece(id,newPiece)
    }
    fun getItemCart(id : Int){
        viewModelScope.launch {
            _isexist.value = repo.getItem(id)
        }
    }
}