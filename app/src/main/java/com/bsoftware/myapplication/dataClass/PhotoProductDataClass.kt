package com.bsoftware.myapplication.dataClass

import com.google.gson.annotations.SerializedName

data class PhotoProductDataClass(
    @SerializedName("id")           var id           : Int?,
    @SerializedName("titleProduct") var titleProduct : String?,
    @SerializedName("descProduct")  var descProduct  : String?,
    @SerializedName("priceProduct") var priceProduct : String?,
    @SerializedName("photo")        var photo        : String?
): java.io.Serializable