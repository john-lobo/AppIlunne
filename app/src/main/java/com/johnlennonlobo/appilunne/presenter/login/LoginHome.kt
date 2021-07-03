package com.johnlennonlobo.appilunne.presenter.login

interface LoginHome {
    interface Presenter{
        fun request(email:String,senha:String,typeAccess:Boolean)
        fun onSuccess(message: String)
        fun onError(message: String)
        fun onIncompleteInfo(message: String)
        fun onComplete()
    }

}