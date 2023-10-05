package com.bsoftware.myapplication.sharePreference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharePreference(var activity : Activity) {

    private val sharePreference = activity.getSharedPreferences("IsLoginState", Context.MODE_PRIVATE)
    private val shareEdit = sharePreference.edit()


    fun setLoginState(isLogin : Boolean){
        shareEdit.putBoolean(loginKey,isLogin)
        shareEdit.commit()
    }

    fun getLoginState() : Boolean{
        return sharePreference.getBoolean(loginKey,false)
    }


    // function for get name and numberphone
    fun setName(name : String){
        shareEdit.putString(nameKey,name)
        shareEdit.commit()
    }

    fun getName() : String?{
        return sharePreference.getString(nameKey,"username")
    }

    fun setPhoneNum(phoneNum : String){
        shareEdit.putString(phoneKey,phoneNum)
        shareEdit.commit()
    }

    fun getPhoneNum() : String?{
        return sharePreference.getString(phoneKey,"phonenum")
    }
    companion object{
        private val loginKey : String = "onLoginState"
        private val nameKey : String = "nameFully"
        private val phoneKey : String = "numphoneKey"
    }

}