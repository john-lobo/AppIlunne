package com.johnlennonlobo.appilunne.presenter.login

import com.johnlennonlobo.appilunne.model.data.AppDataSource
import com.johnlennonlobo.appilunne.presenter.ViewHome

class LoginPresenter(
        val view : ViewHome.View,
        val dataSouce: AppDataSource
): LoginHome.Presenter {
    override fun request(email:String,senha:String,typeAccess:Boolean) {
        this.view.showProgressBar()
        this.dataSouce.getAuthentication(this,email,senha,typeAccess)
    }

    override fun onSuccess(message: String) {
        this.view.showSuccess(message)
    }

    override fun onError(message: String) {
       this.view.showFailure(message)
    }

    override fun onIncompleteInfo(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}