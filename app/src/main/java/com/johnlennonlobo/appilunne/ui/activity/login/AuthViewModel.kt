package com.johnlennonlobo.appilunne.ui.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.*
import com.johnlennonlobo.appilunne.model.Usuario
import com.johnlennonlobo.appilunne.data.repository.AuthRepository
import com.johnlennonlobo.appilunne.utils.Constants.Companion.DELAY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel(), AuthResponse {

    private var mSucessMessage = MutableLiveData<String>()
    val sucessMessage = mSucessMessage

    private val mErrorMessage = MutableLiveData<String>()
    val errorMessage = mErrorMessage

    private val mHideProgressBar=MutableLiveData<Boolean>()
    val hideProgressBar=mHideProgressBar

    fun getAuthentication(tipoAcesso: Boolean, usuario: Usuario) {
        hideProgressBar(false)
        GlobalScope.launch(Dispatchers.Main) {
            delay(DELAY)
            if (usuario.email.isNotEmpty()) {
                if (usuario.senha.isNotEmpty()) {

                    if(tipoAcesso){
                        AuthRepository.register(
                            usuario, this@AuthViewModel)
                    }else{
                        AuthRepository.login(
                            usuario, this@AuthViewModel)
                    }

                } else {
                    onFailure("Preencha a senha!")
                }
            } else {
                onFailure("Preencha o email!")
            }

        }

    }


    override fun hideProgressBar(hide:Boolean) {
        mHideProgressBar.postValue(hide)
    }

    override fun onSuccess(message: String) {
        mSucessMessage.postValue(message)
        hideProgressBar(true)
    }

    override fun onFailure(message: String) {
        mErrorMessage.postValue(message)
        mHideProgressBar.postValue(true)
        hideProgressBar(true)
    }

}

