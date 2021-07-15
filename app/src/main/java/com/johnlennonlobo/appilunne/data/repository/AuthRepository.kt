package com.johnlennonlobo.appilunne.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.johnlennonlobo.appilunne.data.AuthDataSource
import com.johnlennonlobo.appilunne.model.Usuario
import com.johnlennonlobo.appilunne.ui.activity.login.AuthResponse

class AuthRepository {

    companion object {
        fun login(
            usuario: Usuario,
            callBack: AuthResponse) = AuthDataSource.loginUser(usuario,callBack)

        fun register(
            usuario: Usuario,
            callBack: AuthResponse) = AuthDataSource.registerUser(usuario,callBack)

    }

}