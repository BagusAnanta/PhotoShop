package com.bsoftware.myapplication.firebaseCloud

import android.util.Log
import com.bsoftware.myapplication.dataClass.CheckOutDataClass
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FireBase {

   private lateinit var databasepreference : DatabaseReference


   fun initDatabase(){
       databasepreference = Firebase.database.reference
   }

    fun writeDataCheckOut(idProject : String,name : String,numberPhone : String,date : String){
        val userCheckOut = CheckOutDataClass(name,numberPhone,date)
        databasepreference.child("checkOut").child(idProject).setValue(userCheckOut)
            .addOnSuccessListener {
                Log.d("OnDataSaver","Data Complete Insert")
            }
            .addOnFailureListener {
                Log.e("OnDataSaver","Data Failed Insert")
            }
    }






}