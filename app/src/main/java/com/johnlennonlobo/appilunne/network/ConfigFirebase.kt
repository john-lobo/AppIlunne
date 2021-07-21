package com.johnlennonlobo.appilunne.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ConfigFirebase {

    companion object {

        private lateinit var database: DatabaseReference
        private lateinit var referenceAuthentication: FirebaseAuth
        private lateinit var referenceStorage: StorageReference


        fun getUserId():String{
            val authentication = getFirebaseAuthentication()
            return  authentication.currentUser?.uid.toString()
        }

        //referencia do database
        fun getDatabase(): DatabaseReference{
                database = Firebase.database.reference
            return database
        }

        // refencia do firebase
        fun getFirebaseAuthentication(): FirebaseAuth{
                referenceAuthentication = Firebase.auth
            return referenceAuthentication
        }

        fun getFirebaseStorage(): StorageReference{
            if(referenceStorage == null ){
                referenceStorage = FirebaseStorage.getInstance().reference
            }
            return referenceStorage
        }
    }



}