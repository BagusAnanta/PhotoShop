package com.bsoftware.myapplication.firebaseCloud

import android.util.Log
import com.bsoftware.myapplication.dataClass.CheckOutDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FireBase {

   private lateinit var databasepreference : DatabaseReference

   fun initDatabase() : DatabaseReference{
       databasepreference = Firebase.database("https://candoapp-ef10f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
       return databasepreference
   }

    fun writeDataCheckOut(idProject : String,name : String,numberPhone : String,date : String,projectType : String){
        val userCheckOut = CheckOutDataClass(idProject,name,numberPhone,date,projectType)
        databasepreference.child("checkOut").setValue(userCheckOut)
            .addOnSuccessListener {
                Log.d("OnDataSaver","Data Complete Insert")
            }
            .addOnFailureListener {
                Log.e("OnDataSaver","Data Failed Insert")
            }
    }

}