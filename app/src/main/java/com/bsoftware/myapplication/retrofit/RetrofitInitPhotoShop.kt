package com.bsoftware.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitPhotoShop {
    // we not use this again
    var retrofitPhotoShop = Retrofit.Builder()
        .baseUrl("http://192.168.100.11/PhotoshopApp_Database/productPhotoDatabase/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}