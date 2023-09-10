package com.example.e_commerce.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.repos.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private var repository: ProfileRepository): ViewModel() {
    fun getUser() = repository.getUser()
    fun signOut() = repository.signOut()
}