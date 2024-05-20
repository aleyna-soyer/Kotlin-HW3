package com.aleyna.kotlin_hw3.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleyna.kotlin_hw3.model.Products
import com.aleyna.kotlin_hw3.service.ProductAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private  val productAPI=ProductAPIService()

    val productsData = MutableLiveData<List<Products>>()
    val productsLoad = MutableLiveData<Boolean>()
    val productsError = MutableLiveData<Boolean>()

    fun getDataFromAPI(){
        productsLoad.value= true
        productAPI.getData().enqueue(object: Callback<List<Products>>{
            override fun onResponse(
                call: Call<List<Products>>,
                response: Response<List<Products>>
            ) {
                productsData.value=response.body()
                productsLoad.value=false
                productsError.value=false
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                productsLoad.value=false
                productsError.value=true
                Log.e("RetrofitError", t.message.toString())
            }

        })

    }
}