package com.bsoftware.myapplication.sharePreference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharePreference(var activity : Activity) {

    private val sharePreferenceLogin = activity.getSharedPreferences("LoginStatePref",Context.MODE_PRIVATE)
    private val sharePrefEdit = sharePreferenceLogin.edit()

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
        sharePrefEdit.apply {
            putBoolean(loginKey,isLogin)
            commit()
        }
    }

    fun getLoginState() : Boolean{
        return sharePreferenceLogin.getBoolean(loginKey,false)
    }

    fun deleteLoginState(){
       sharePrefEdit.apply {
           remove(loginKey)
           putBoolean(loginKey,false)
           commit()
       }
    }

    fun deleteLoginStateData(){
        sharePrefEdit.apply {
            remove(loginKey)
            commit()
        }
    }

    fun setLoginAdminState(isLogin : Boolean){
       sharePrefEdit.apply {
           putBoolean(adminLoginKey,isLogin)
           commit()
       }
    }

    fun getLoginAdminState() : Boolean{
        return sharePreferenceLogin.getBoolean(adminLoginKey,false)
    }


    // function for get name and numberphone
    fun setName(name : String){
        sharePrefEdit.apply {
            putString(nameKey,name)
            commit()
        }
    }

    fun getName() : String?{
        return sharePreferenceLogin.getString(nameKey,"username")
    }

    fun deleteName(){
        sharePrefEdit.apply {
            remove(nameKey)
            commit()
        }
    }

    fun setPhoneNum(phoneNum : String){
       sharePrefEdit.apply {
           putString(phoneKey,phoneNum)
           commit()
       }
    }

    fun getPhoneNum() : String?{
        return sharePreferenceLogin.getString(phoneKey,"phonenum")
    }

    fun deletePhoneNum(){
       sharePrefEdit.apply {
           remove(phoneKey)
           commit()
       }
    }

    fun setEmail(email : String){
        sharePrefEdit.apply {
            putString(emailKey,email)
            commit()
        }
    }

    fun getEmail() : String?{
        return sharePreferenceLogin.getString(emailKey,"email")
    }

    fun deleteEmail(){
       sharePrefEdit.apply {
           remove(emailKey)
           commit()
       }
    }
    fun deleteAll(){
        deleteLoginStateData()
        deleteName()
        deletePhoneNum()
        deleteEmail()
    }


    companion object{
        private val loginKey : String = "onLoginState"
        private val nameKey : String = "nameFully"
        private val phoneKey : String = "numphoneKey"
        private val adminLoginKey : String = "adminKey"
        private val emailKey : String = "emailKey"

        // for product choose
        private val productKey : String = "productChooseKey"
        private val imagecodeKey : String = "imageChooseKey"
    }

}