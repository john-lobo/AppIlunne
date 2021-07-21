package com.johnlennonlobo.appilunne.model

import com.google.firebase.database.Exclude
import com.johnlennonlobo.appilunne.network.ConfigFirebase
import com.johnlennonlobo.appilunne.utils.Constants.Companion.CHILD_USUARIOS

class Usuario(
     var id: String = "",
     var nome: String = "",
     var email:String = "",
     var tipo: String = "",
     @Exclude @set:Exclude @get:Exclude var senha:String = ""
){

     fun salvar() {
          val database = ConfigFirebase.getDatabase()
          database.child(CHILD_USUARIOS)
               .child("$id")
               .setValue(this)

     }
}