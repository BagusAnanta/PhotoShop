package com.bsoftware.myapplication.loginDataClass

import com.google.gson.annotations.SerializedName

data class LoginDataClass(
    @SerializedName("username") var username : String?,
    @SerializedName("password") var password : String?
) : java.io.Serializable
