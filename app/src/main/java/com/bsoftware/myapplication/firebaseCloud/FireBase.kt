package com.bsoftware.myapplication.firebaseCloud

import android.util.Log
import com.bsoftware.myapplication.dataClass.CheckOutDataClass
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


   fun initDatabase(){
       databasepreference = Firebase.database("https://candoapp-ef10f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
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

    // I Think i can use this later after we make a
   /* fun getDataCheckOut(){

        val postListener = object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val getdata = snapshot.getValue(CheckOutDataClass::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databasepreference.addValueEventListener(postListener)
    }*/






}