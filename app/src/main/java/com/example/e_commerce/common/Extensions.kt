package com.example.e_commerce.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.example.e_commerce.R
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadImage(url : String,context: Context){
   Glide.with(context).load(url).transform(RoundedCorners(20)).placeholder(R.drawable.baseline_loading_24).into(this)
}
fun View.showToast(message : String){
   Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
fun View.showSnackbar(message: String){
   Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}