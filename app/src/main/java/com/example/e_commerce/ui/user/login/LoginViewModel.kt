package com.example.e_commerce.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.data.repos.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn : LiveData<Boolean>
        get() = _isSignIn
    private var _isEmpty = MutableLiveData<Boolean>()
    val isEmpty : LiveData<Boolean>
        get() = _isEmpty
    init {
        _isSignIn = userRepository.isSignIn
        _isEmpty = userRepository.isEmptySignIn
    }
    fun signIn(email : String,password : String) =  userRepository.signIn(email, password)
}