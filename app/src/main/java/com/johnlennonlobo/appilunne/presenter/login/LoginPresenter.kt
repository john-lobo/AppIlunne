package com.johnlennonlobo.appilunne.presenter.login

//class LoginPresenter(
//        val view : ViewHome.View,
//        val viewModel: LoginViewModel
//
//): LoginHome.Presenter {
//    override fun request(context: Context, email:String, senha:String, typeAccess:Boolean) {
//        this.view.showProgressBar()
//        this.viewModel.getAuthentication(context,this,email,senha,typeAccess)
//    }
//
//    override fun onSuccess(message: String) {
//        this.view.showSuccess(message)
//    }
//
//    override fun onError(message: String) {
//       this.view.showFailure(message)
//    }
//
//    override fun onIncompleteInfo(message: String) {
//        this.view.showFailure(message)
//    }
//
//    override fun onComplete() {
//        this.view.hideProgressBar()
//    }
//}