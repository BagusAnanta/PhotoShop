package com.bsoftware.myapplication.dataViewModelClass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsoftware.myapplication.loginAPI.LoginInterfaceAPI
import com.bsoftware.myapplication.loginDataClass.LoginDataClass
import com.bsoftware.myapplication.retrofit.RetrofitInit
import kotlinx.coroutines.launch
import retrofit2.create

class DataViewModelClass : ViewModel() {
    private val apiInterface = RetrofitInit().retrofit.create(LoginInterfaceAPI::class.java)
    private val _Datauser = MutableLiveData<List<LoginDataClass>>()
    val Datauser : LiveData<List<LoginDataClass>> = _Datauser

    fun getDataLogin(){
        viewModelScope.launch {
            try{
                val fetchDataUser = apiInterface.getData()
                _Datauser.postValue(fetchDataUser)
            } catch (e : Exception){
                // Connection Lost
                Log.e("DataViewModel Exception",e.toString())
            }
        }
    }

    fun insertDataLogin(username : String?,password : String?){
        val TAG = "InsertDataResult"
        viewModelScope.launch {
            try {
                val callDataLogin = apiInterface.insertData(username,password)
            } catch (e : Exception){
                Log.e(TAG,e.toString())
            }

        }
    }
}