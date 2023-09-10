package com.example.e_commerce.data.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.common.Constants
import com.example.e_commerce.common.Constants.NAME
import com.example.e_commerce.common.Constants.PHONE_NUMBER
import com.example.e_commerce.common.Constants.USER
import com.example.e_commerce.common.Constants.USER_EMAIL
import com.example.e_commerce.common.Singleton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class UserRepository @Inject constructor(private val firebaseAuth: FirebaseAuth,private val fireStore: FirebaseFirestore){
    val isSignUp = MutableLiveData<Boolean>()
    val isEmptySignUp = MutableLiveData<Boolean>()
    val isEmptySignIn = MutableLiveData<Boolean>()

    val isSaved = MutableLiveData<Boolean>()
    val isSignIn = MutableLiveData<Boolean>()
    fun signUp(email : String,password : String,nameLastname : String,phoneNumber : String){

        if (email.isEmpty() || password.isEmpty() || nameLastname.isEmpty() || phoneNumber.isEmpty()){
            isEmptySignUp.value = true
        }else{
            isEmptySignUp.value = false
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                isSignUp.value = true
                val userMap = hashMapOf<String,String>(
                    NAME to nameLastname,
                    PHONE_NUMBER to phoneNumber,
                    USER_EMAIL to email
                )
                fireStore.collection(USER).document(email).set(userMap).addOnCompleteListener {
                    if (it.isSuccessful){
                        isSaved.value = true
                    }
                }

            }.addOnFailureListener{
                isSignUp.value = false
            }
        }

    }
    fun signIn(email: String,password: String){
        if (email.isEmpty() || password.isEmpty()){
            isEmptySignIn.value = true
        }else{
            isEmptySignIn.value = false
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                isSignIn.value = task.isSuccessful
                isSignIn.value = false
            }
        }

    }

}