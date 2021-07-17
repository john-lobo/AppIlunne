package com.johnlennonlobo.appilunne.utils

import com.johnlennonlobo.appilunne.network.ConfigFirebase

class Constants {
    companion object{

        const val TIPO_USUARIO = "USU"

        val CHILD_ID_UNICO : String = ConfigFirebase.getUserId()
        const val CHILD_USUARIOS = "usuarios"
        const val CHILD_NOME = "nome"
        const val CHILD_EMAIL = "email"
        const val CHILD_TIPO = "USU"
        const val CHILD_ID = "id"


        const val DELAY = 500L

    }
}