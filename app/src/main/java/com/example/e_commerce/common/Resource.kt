package com.example.e_commerce.common

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val msg: String) : Resource<Nothing>()
}