package com.bsoftware.myapplication.sharePreference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharePreference(var activity : Activity) {

    private val sharePreference = activity.getSharedPreferences("IsLoginState", Context.MODE_PRIVATE)
    private val shareEdit = sharePreference.edit()
    private val loginKey : String = "onLoginState"


    fun setLoginState(isLogin : Boolean){
        shareEdit.putBoolean(loginKey,isLogin)
        shareEdit.commit()
    }

    fun getLoginState() : Boolean{
        return sharePreference.getBoolean(loginKey,false)
    }

}