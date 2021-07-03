package com.johnlennonlobo.appilunne.model.data

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.*
import com.johnlennonlobo.appilunne.ui.presenter.login.LoginHome
import com.johnlennonlobo.appilunne.ui.ui.MainActivity
import com.johnlennonlobo.appilunne.utils.Constants.Companion.DELAY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class AppDataSource(val authentication: FirebaseAuth,val context: Context) {

    fun getAuthentication(
            calback: LoginHome.Presenter,
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
                                calback.onSuccess("Cadastro realizado com sucesso")
                            } else {
                                try {
                                    throw task.exception!!
                                } catch (error: FirebaseAuthWeakPasswordException) {
                                    calback.onError("Digite uma senha mais forte")
                                } catch (error: FirebaseAuthInvalidCredentialsException) {
                                    calback.onError("Digite um email valido")
                                } catch (error: FirebaseAuthUserCollisionException) {
                                    calback.onError("Esta conta ja esta cadastrada")
                                } catch (error: Exception) {
                                    calback.onError("Erro ao cadastrar conta ${error.message}")
                                }

                            }
                            calback.onComplete()
                        }
                    }
                    else{
                        authentication.signInWithEmailAndPassword(email,senha).addOnCompleteListener { task ->
                         if(task.isSuccessful){
                             calback.onSuccess("Usuario logado")
                             val intent = Intent(context.applicationContext, MainActivity::class.java)
                             context.startActivity(intent)
                         }else{
                             try {
                                 throw task.exception!!
                             }
                            catch (error: Exception) {
                                 calback.onError("Erro ao logar conta ${error.message}")
                             }
                         }
                        }
                        calback.onComplete()
                    }

                } else {
                    calback.onIncompleteInfo("Preencha a senha!")
                    calback.onComplete()
                }
            } else {
                calback.onIncompleteInfo("Preencha o email!")
                calback.onComplete()
            }
        }
    }
}