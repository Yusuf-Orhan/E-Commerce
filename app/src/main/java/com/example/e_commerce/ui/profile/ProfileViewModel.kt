package com.example.e_commerce.ui.profile

import androidx.lifecycle.ViewModel
import com.example.e_commerce.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private var repository: ProfileRepository): ViewModel() {
    fun getUser() = repository.getUser()
    fun signOut() = repository.signOut()
}