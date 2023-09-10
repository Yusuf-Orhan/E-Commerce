package com.example.e_commerce.data.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.model.room.ProductModel
import com.example.e_commerce.data.room.ProductDao
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: ProductDao){
    val allProductList = MutableLiveData<List<ProductModel>>()

    suspend fun insertItem(productsItem: ProductsItem,piece : Int){
        dao.insert(productModel = ProductModel(productsItem.category,productsItem.description,productsItem.id,productsItem.image,productsItem.price,productsItem.rating.rate,piece,productsItem.title))
    }
    suspend fun getData() : List<ProductModel>{
        return dao.getAllCart()
    }
    suspend fun getAllProducts(){
        dao.getAllCart().also {
            allProductList.value = it
        }
    }
    suspend fun updatePiece(id : Int,newPiece : Int){
        dao.updatePiece(id,newPiece)
    }
    suspend fun controlProduct(uid : Int) : Boolean?{
        val productModel = dao.controlProduct(uid)
        return if (productModel != null){
            productModel.title.isEmpty()
        }else{
            null
        }

    }
}