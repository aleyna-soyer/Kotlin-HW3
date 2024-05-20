package com.aleyna.kotlin_hw3.service

import com.aleyna.kotlin_hw3.model.Products
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ProductAPIService {

    //https://fakestoreapi.com/products

    private val BASE_URL = "https://fakestoreapi.com/"

    private val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductAPI::class.java)

    fun getData(): Call<List<Products>>{
        return api.getProducts()
    }
}