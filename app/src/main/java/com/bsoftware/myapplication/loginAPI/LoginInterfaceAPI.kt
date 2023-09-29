package com.bsoftware.myapplication.loginAPI

import com.bsoftware.myapplication.loginDataClass.LoginDataClass
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginInterfaceAPI {

    @GET("Read.php")
    suspend fun getData() : List<LoginDataClass>

    @FormUrlEncoded
    @POST("Create.php")
    suspend fun insertData(
        @Field("username") username : String?,
        @Field("password") password : String?
    ) : Response<LoginDataClass>
}