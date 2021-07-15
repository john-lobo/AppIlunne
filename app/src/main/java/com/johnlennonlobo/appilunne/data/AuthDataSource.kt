package com.johnlennonlobo.appilunne.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.johnlennonlobo.appilunne.model.Usuario
import com.johnlennonlobo.appilunne.network.ConfigFirebase
import com.johnlennonlobo.appilunne.ui.activity.login.AuthResponse
import java.lang.Exception

class AuthDataSource {

    companion object {
        private val auth: FirebaseAuth=ConfigFirebase.getFirebaseAuthentication()

        fun loginUser(usuario: Usuario, callBack: AuthResponse) {
            auth.signInWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callBack.onSuccess("Usuario logado")
                    } else {
                        try {
                            throw task.exception!!
                        } catch (error: Exception) {
                            callBack.onFailure("Erro ao logar conta ${error.message}")
                        }
                    }
                }

        }

        fun registerUser(usuario: Usuario, callBack: AuthResponse) {
            auth.createUserWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callBack.onSuccess("Usuario cadastrado com sucesso")
                    } else {
                        try {
                            throw task.exception!!
                        } catch (error: FirebaseAuthWeakPasswordException) {
                            callBack.onFailure("Digite uma senha mais forte")
                        } catch (error: FirebaseAuthInvalidCredentialsException) {
                            callBack.onFailure("Digite um email valido")
                        } catch (error: FirebaseAuthUserCollisionException) {
                            callBack.onFailure("Esta conta ja esta cadastrada")
                        } catch (error: Exception) {
                            callBack.onFailure("Erro ao cadastrar conta ${error.message}")
                        }
                    }
                }

        }
    }

}