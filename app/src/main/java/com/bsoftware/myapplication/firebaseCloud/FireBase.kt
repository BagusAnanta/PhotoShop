package com.bsoftware.myapplication.firebaseCloud

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FireBase {

    /*I dont know why how a console show a error message im gonna back im find more error for solve
    * */

    var storage : FirebaseStorage = FirebaseStorage.getInstance()
    var storageRef  = storage.reference

    // create function for reference
    fun storageChild(uri : String){storageRef.child(uri)}

}