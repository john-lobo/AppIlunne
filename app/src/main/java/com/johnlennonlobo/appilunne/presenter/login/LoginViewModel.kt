package com.johnlennonlobo.appilunne.presenter.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.*
import com.johnlennonlobo.appilunne.ui.activity.main_home.MainHomeActivity
import com.johnlennonlobo.appilunne.utils.Constants.Companion.DELAY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(val authentication: FirebaseAuth): ViewModel(){

    val sucessMessage = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

    fun getAuthentication(
            context: Context,
            email: String,
            senha: String,
            tipoAcesso: Boolean,
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            delay(DELAY)
            if (email.isNotEmpty()) {
                if (senha.isNotEmpty()) {
                    if (tipoAcesso) {
                        authentication.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                sucessMessage.postValue("Usuario cadastrado com sucesso")
                            } else {
                                try {
                                    throw task.exception!!
                                } catch (error: FirebaseAuthWeakPasswordException) {
                                    errorMessage.postValue("Digite uma senha mais forte")
                                } catch (error: FirebaseAuthInvalidCredentialsException) {
                                    errorMessage.postValue("Digite um email valido")
                                } catch (error: FirebaseAuthUserCollisionException) {
                                    errorMessage.postValue("Esta conta ja esta cadastrada")
                                } catch (error: Exception) {
                                    errorMessage.postValue("Erro ao cadastrar conta ${error.message}")
                                }

                            }
                        // calback.onComplete()
                        }
                    } else {
                        authentication.signInWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                sucessMessage.postValue("Usuario logado")
                                val intent=Intent(context.applicationContext, MainHomeActivity
                                ::class.java)
                                context.startActivity(intent)
                            } else {
                                try {
                                    throw task.exception!!
                                } catch (error: Exception) {
                                    errorMessage.postValue("Erro ao logar conta ${error.message}")
                                }
                            }
                        }

                    }

                } else {
                    errorMessage.postValue("Preencha a senha!")
                }
            } else {
                errorMessage.postValue("Preencha o email!")
            }
        }
    }
}