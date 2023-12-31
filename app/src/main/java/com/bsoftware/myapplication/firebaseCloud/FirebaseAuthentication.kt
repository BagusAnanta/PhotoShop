package com.bsoftware.myapplication.firebaseCloud

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class FirebaseAuthentication {

    private lateinit var auth : FirebaseAuth

    fun initFirebaseAuth(){
        auth = Firebase.auth
    }

    fun createDataUserEmailPass(
        email : String,
        password : String,
        activity : Activity,
        onSuccess : () -> Unit = {},
        onFailed : () -> Unit = {},
    ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // you can get data in here
                    onSuccess()

                } else {
                    // If sign in fails, display a message to the user.
                    onFailed()
                    // check a exception inhere

                    val exception = task.exception
                    Log.e("Create Data User Exception At :",exception.toString())
                }
            }
    }

    fun signInDataUserEmailPass(
        email : String,
        password : String,
        activity : Activity,
        onSuccess: () -> Unit = {},
        onFail : () -> Unit = {}
    ){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // if a signIn Success we gonna intent into main menu from onSuccess lambda function
                    onSuccess()

                } else {
                    // If sign in fails, display a message to the user.
                    onFail()

                }
            }
    }

    fun getEmail() : String{
        return auth.currentUser?.email!!
    }

    fun getUserName() : String?{
        return auth.currentUser?.displayName
    }

    fun addUpdateUserDisplayName(userName : String){
        val user = Firebase.auth.currentUser
        val updateNameDisplay = userProfileChangeRequest {
            displayName = userName
        }

        user?.updateProfile(updateNameDisplay)?.addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("OnUpdateDisplayName","DisplayNameSuccessAddorUpdate")
            }
        }
    }

    fun signOutEmail(){
        Firebase.auth.signOut()
    }
}