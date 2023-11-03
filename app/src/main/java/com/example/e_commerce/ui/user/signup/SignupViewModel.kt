package com.example.e_commerce.ui.user.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SignupViewModel  @Inject constructor(private val repository: UserRepository): ViewModel() {
    var _isSignUp = MutableLiveData<Boolean>()
    var isEmpty = MutableLiveData<Boolean>()
    var isSaved = MutableLiveData<Boolean>()

    init {
        _isSignUp = repository.isSignUp
        isEmpty = repository.isEmptySignUp
        isSaved = repository.isSaved
    }
    fun signup(email : String,password : String,nameLastname : String,phoneNumber : String) = repository.signUp(email, password,nameLastname, phoneNumber)
}