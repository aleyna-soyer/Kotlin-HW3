package com.aleyna.kotlin_hw3.service

import com.aleyna.kotlin_hw3.model.Products
import retrofit2.Call
import retrofit2.http.GET

interface ProductAPI {

    //https://fakestoreapi.com/products

    @GET("products")
    fun getProducts(): Call<List<Products>>

}