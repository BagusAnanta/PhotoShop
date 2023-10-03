package com.bsoftware.myapplication.dataViewModelClass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsoftware.myapplication.appAPI.PhotoProductAPI
import com.bsoftware.myapplication.dataClass.PhotoProductDataClass
import com.bsoftware.myapplication.retrofit.RetrofitInit
import com.bsoftware.myapplication.retrofit.RetrofitInitPhotoShop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotoShopDataViewModelClass : ViewModel(){

    private val apiInterface = RetrofitInitPhotoShop().retrofitPhotoShop.create(PhotoProductAPI::class.java)
    private val _PhotoShopData = MutableLiveData<List<PhotoProductDataClass>>()
    val PhotoshopData : LiveData<List<PhotoProductDataClass>> = _PhotoShopData

    val items : MutableList<PhotoProductDataClass> = mutableListOf()


    fun getPhotoShopData(){
        val TAG = "DataViewModel Exception"
        viewModelScope.launch {
            try{
                val fetchPhotoShop = apiInterface.getData()
                _PhotoShopData.postValue(fetchPhotoShop)
            } catch (e : Exception){
                // Connection Lost
                Log.e(TAG,e.toString())
            }
        }
    }


    fun insertPhotoShopData(titleProduct : String?,descProduct : String?,priceProduct : String?,photo : String?){
        val TAG = "InsertDataResult"
        viewModelScope.launch {
            try {
                val callDataLogin = apiInterface.insertData(titleProduct,descProduct, priceProduct, photo)
            } catch (e : Exception){
                Log.e(TAG,e.toString())
            }

        }
    }

}