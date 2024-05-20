package com.aleyna.kotlin_hw3.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class Products (
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("image")
    val productUrl: String
)