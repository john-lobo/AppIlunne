package com.johnlennonlobo.appilunne.presenter.login

import android.content.Context

interface LoginHome {
    interface Presenter{
        fun request(context: Context, email:String, senha:String, typeAccess:Boolean)
        fun onSuccess(message: String)
        fun onError(message: String)
        fun onIncompleteInfo(message: String)
        fun onComplete()
    }

}