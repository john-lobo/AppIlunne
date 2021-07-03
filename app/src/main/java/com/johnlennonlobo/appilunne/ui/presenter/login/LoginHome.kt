package com.johnlennonlobo.appilunne.ui.presenter.login

import android.content.Context
import com.johnlennonlobo.appilunne.model.data.AppDataSource
import com.johnlennonlobo.appilunne.model.entity.Usuario

interface LoginHome {
    interface Presenter{
        fun request(email:String,senha:String,typeAccess:Boolean)
        fun onSuccess(message: String)
        fun onError(message: String)
        fun onIncompleteInfo(message: String)
        fun onComplete()
    }

}