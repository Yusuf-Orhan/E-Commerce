package com.example.e_commerce.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.room.FavoriteModel
import com.example.e_commerce.data.repos.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val repo : FavoriteRepository): ViewModel(){
    fun addFavorite(item : FavoriteModel) = viewModelScope.launch {
        repo.addFavorite(item)
    }
    fun deleteFavorite(item : FavoriteModel) = viewModelScope.launch{
        repo.deleteFavorite(item)
    }
}