package com.johnlennonlobo.appilunne.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthViewModelFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                AuthViewModel() as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
