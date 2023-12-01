package com.example.e_commerce.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.common.Constants.NAME
import com.example.e_commerce.common.Constants.PHONE_NUMBER
import com.example.e_commerce.common.Constants.USER
import com.example.e_commerce.common.Constants.USER_EMAIL
import com.example.e_commerce.data.model.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val firebaseAuth: FirebaseAuth,private val firestore: FirebaseFirestore){
    val user = MutableLiveData<User>()
    private var email = ""
    var name = ""
    private var phoneNumber = ""
    fun getUser() : LiveData<User>{
        firestore.collection(USER).document(firebaseAuth.currentUser?.email.toString()).addSnapshotListener { value, error ->
            if (error != null){
               Log.e("User",error.message.orEmpty())
            }else{
                value?.let {
                    val userMap = it.data
                    email = userMap?.get(USER_EMAIL) as String
                    name = userMap[NAME] as String
                    phoneNumber = userMap[PHONE_NUMBER] as String
                    val _user = User(name,phoneNumber, email)
                    user.value = _user
                }

            }
        }
        return user
    }
    fun signOut(){
        firebaseAuth.signOut()
    }
}