package com.johnlennonlobo.appilunne.ui.presenter.login

import android.content.Context
import android.content.Intent
import com.johnlennonlobo.appilunne.model.data.AppDataSource
import com.johnlennonlobo.appilunne.ui.presenter.ViewHome
import com.johnlennonlobo.appilunne.ui.ui.MainActivity

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