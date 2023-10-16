package com.bsoftware.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInit {
    // we gonna change to url in host
    var retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.11/PhotoshopApp_Database/loginUserDatabase/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}