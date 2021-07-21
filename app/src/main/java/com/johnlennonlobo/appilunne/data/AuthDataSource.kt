package com.johnlennonlobo.appilunne.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.johnlennonlobo.appilunne.model.Usuario
import com.johnlennonlobo.appilunne.network.ConfigFirebase
import com.johnlennonlobo.appilunne.ui.activity.login.AuthResponse
import com.johnlennonlobo.appilunne.utils.Constants.Companion.CHILD_ID_UNICO
import com.johnlennonlobo.appilunne.utils.Constants.Companion.CHILD_NOME
import com.johnlennonlobo.appilunne.utils.Constants.Companion.CHILD_USUARIOS
import com.johnlennonlobo.appilunne.utils.Constants.Companion.TIPO_USUARIO

class AuthDataSource {

    companion object {

        private val auth: FirebaseAuth=ConfigFirebase.getFirebaseAuthentication()

        fun loginUser(usuario: Usuario, callBack: AuthResponse) {
            auth.signInWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val getUsuario = ConfigFirebase.getDatabase()
                            .child(CHILD_USUARIOS)
                            .child(CHILD_ID_UNICO)
                            .child(CHILD_NOME)

                        getUsuario.addValueEventListener(object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val nomeUsuario = snapshot.value.toString()
                                if(nomeUsuario == "null"){
                                    callBack.onSuccess("Bem vindo")
                                }else{
                                    callBack.onSuccess("Bem vindo $nomeUsuario")
                                }

                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })

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

                        val idUsuario = task.result?.user?.uid
                        usuario.id = idUsuario!!
                        usuario.tipo = TIPO_USUARIO

                        usuario.salvar()

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