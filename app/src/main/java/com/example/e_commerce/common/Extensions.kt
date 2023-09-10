package com.example.e_commerce.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url : String){
   Picasso.get().load(url).into(this)
}
fun View.showToast(message : String){
   Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
fun View.showSnackbar(message: String){
   Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}