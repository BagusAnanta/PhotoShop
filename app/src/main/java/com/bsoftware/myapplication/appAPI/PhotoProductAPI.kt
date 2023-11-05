package com.bsoftware.myapplication.appAPI

import com.bsoftware.myapplication.dataClass.PhotoProductDataClass
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PhotoProductAPI {

    // get data use id
    @GET("Read.php/{id}")
    suspend fun getDataId(@Path("id") id: Int) : List<PhotoProductDataClass>

    // get more data non use id
    @GET("Read.php")
    suspend fun getData() : List<PhotoProductDataClass>

    @FormUrlEncoded
    @POST("Create.php")
    suspend fun insertData(
        @Field("titleProduct") titleProduct : String?,
        @Field("descProduct")  descProduct : String?,
        @Field("priceProduct") priceProduct : String?,
        @Field("photo")        photo : String?
    ) : Response<PhotoProductDataClass>




}