package com.bsoftware.myapplication.sharePreference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharePreference(var activity : Activity) {

    private val sharePreference = activity.getSharedPreferences("IsLoginState", Context.MODE_PRIVATE)
    private val shareEdit = sharePreference.edit()

    // for save a choice data
    private val sharePreferenceChoose = activity.getSharedPreferences("UserProductChoose", Context.MODE_PRIVATE)
    private val shareEditChoose = sharePreferenceChoose.edit()

    fun setProductName(productName : String){
        shareEditChoose.putString(productKey,productName)
        shareEditChoose.commit()
    }

    fun getProductName() : String{
        return sharePreferenceChoose.getString(productKey,"Product")!!
    }

    fun deleteProductName(){
        shareEditChoose.remove(productKey)
        shareEditChoose.putString(productKey,"productName")
        shareEditChoose.commit()
    }

    fun setImageProduct(imageProduct : Int){
        shareEditChoose.putInt(imagecodeKey,imageProduct)
        shareEditChoose.commit()
    }

    fun getImageProduct() : Int{
        return sharePreferenceChoose.getInt(imagecodeKey,0)
    }

    fun deleteImageName(){
        shareEditChoose.remove(imagecodeKey)
        shareEditChoose.putInt(imagecodeKey,0)
        shareEditChoose.commit()
    }


    fun deleteProductChooseAll(){
        deleteProductName()
        deleteImageName()
    }


    fun setLoginState(isLogin : Boolean){
        shareEdit.putBoolean(loginKey,isLogin)
        shareEdit.commit()
    }

    fun getLoginState() : Boolean{
        return sharePreference.getBoolean(loginKey,false)
    }

    fun deleteLoginState(){
        shareEdit.remove(loginKey)
        shareEdit.putBoolean(loginKey,false)
        shareEdit.commit()
    }

    fun deleteLoginStateData(){
        shareEdit.remove(loginKey)
        shareEdit.commit()
    }

    fun setLoginAdminState(isLogin : Boolean){
        shareEdit.putBoolean(adminLoginKey,isLogin)
        shareEdit.commit()
    }

    fun getLoginAdminState() : Boolean{
        return sharePreference.getBoolean(adminLoginKey,false)
    }


    // function for get name and numberphone
    fun setName(name : String){
        shareEdit.putString(nameKey,name)
        shareEdit.commit()
    }

    fun getName() : String?{
        return sharePreference.getString(nameKey,"username")
    }

    fun deleteName(){
        shareEdit.remove(nameKey)
        shareEdit.commit()
    }

    fun setPhoneNum(phoneNum : String){
        shareEdit.putString(phoneKey,phoneNum)
        shareEdit.commit()
    }

    fun getPhoneNum() : String?{
        return sharePreference.getString(phoneKey,"phonenum")
    }

    fun deletePhoneNum(){
        shareEdit.remove(phoneKey)
        shareEdit.commit()
    }

    fun deleteAll(){
        deleteLoginStateData()
        deleteName()
        deletePhoneNum()
    }


    companion object{
        private val loginKey : String = "onLoginState"
        private val nameKey : String = "nameFully"
        private val phoneKey : String = "numphoneKey"
        private val adminLoginKey : String = "adminKey"

        // for product choose
        private val productKey : String = "productChooseKey"
        private val imagecodeKey : String = "imageChooseKey"
    }

}