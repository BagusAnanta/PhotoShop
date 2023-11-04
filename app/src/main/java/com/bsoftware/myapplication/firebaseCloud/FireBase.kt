package com.bsoftware.myapplication.firebaseCloud

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.bsoftware.myapplication.dataClass.CheckOutDataClass
import com.bsoftware.myapplication.dataClass.UserDataClass
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
   private var email : String = ""
       set(value) {field = value}
       get() = field

   private var name : String = ""
       set(value) {field = value}
       get() = field

   private var numPhone : String = ""
       set(value) {field = value}
       get() = field

   fun initDatabase() : DatabaseReference{
       databasepreference = Firebase.database("https://candoapp-ef10f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
       return databasepreference
   }

    fun writeDataCheckOut(idProject : String,name : String,numberPhone : String,date : String,projectType : String,email : String){
        val userCheckOut = CheckOutDataClass(idProject,name,numberPhone,date,projectType,email)
        databasepreference.child("checkOut").child(idProject).setValue(userCheckOut)
            .addOnSuccessListener {
                Log.d("OnDataSaver","Data Complete Insert")
            }
            .addOnFailureListener {
                Log.e("OnDataSaver","Data Failed Insert")
            }
    }

    fun writeDataUser(userId : String,email: String,name: String,numberPhone: String){
        val dataUser = UserDataClass(userId,email,name,numberPhone)
        databasepreference.child("userData").child(userId).setValue(dataUser)
            .addOnSuccessListener {
                Log.d("OnDataSaver","Data Complete Insert")
            }
            .addOnFailureListener {
                Log.e("OnDataSaver","Data Failed Insert")
            }
    }

}