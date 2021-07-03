package com.johnlennonlobo.appilunne.ui.presenter

interface ViewHome {

    interface View{
        fun showProgressBar()
        fun hideProgressBar()
        fun showFailure(message:String)
        fun showSuccess(message:String)
    }
}