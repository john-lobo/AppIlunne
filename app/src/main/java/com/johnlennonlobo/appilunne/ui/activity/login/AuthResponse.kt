package com.johnlennonlobo.appilunne.ui.activity.login

interface AuthResponse {
    fun hideProgressBar(hide:Boolean)
    fun onSuccess(message: String)
    fun onFailure(message: String)
}