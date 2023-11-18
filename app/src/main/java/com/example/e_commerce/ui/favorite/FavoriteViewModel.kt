package com.example.e_commerce.ui.favorite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.room.FavoriteModel
import com.example.e_commerce.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repo: FavoriteRepository
) : ViewModel() {
    val favoriteList = MutableLiveData<List<FavoriteModel>>()
    fun getFavorite() = viewModelScope.launch {
        favoriteList.value = repo.getFavorite()
    }
}


data class FavoriteState(
    val favoriteList: List<FavoriteModel> = emptyList(),
    val empty: Boolean = true
)