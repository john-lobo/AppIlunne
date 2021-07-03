package com.johnlennonlobo.appilunne.ui.ui

import android.app.ActionBar
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.johnlennonlobo.appilunne.R
import com.johnlennonlobo.appilunne.model.data.AppDataSource
import com.johnlennonlobo.appilunne.ui.helper.ConfigFirebase
import com.johnlennonlobo.appilunne.ui.presenter.ViewHome
import com.johnlennonlobo.appilunne.ui.presenter.login.LoginPresenter
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_BOTTOM
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_LEFT
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_RIGHT
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_TOP
import kotlinx.android.synthetic.main.authentic_activity.*


class AuthenticActivity : AbstractActivity(), ViewHome.View {
    private lateinit var params: LinearLayout.LayoutParams
    private lateinit var presenter: LoginPresenter
    private lateinit var authentication: FirebaseAuth

    //TODO implements AbstractActivity
    override fun getLayout(): Int =R.layout.authentic_activity

    override fun getObject() {
        supportActionBar?.hide()
        authentication = ConfigFirebase
                .getFirebaseAuthentication()

        val dataSource = AppDataSource(authentication, this)
        presenter = LoginPresenter(this, dataSource)
        signOrRegister()
        configEdtEmail()

    }

    //TODO implements ViewHome.View
    override fun showProgressBar() {
        progressBarLoginID.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBarLoginID.visibility = View.INVISIBLE
    }

    override fun showFailure(message: String) {
        showMessage(message)
    }

    override fun showSuccess(message: String) {
        showMessage(message)
    }

    //TODO My Functions created in this
    private fun showMessage(message: String) {
        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT).show()
    }

    private fun signOrRegister() {
        with(btnSignOrRegister){
            setOnClickListener {
                val email = edtEmailLogin_ID.text.toString()
                val senha = edtSenhalLogin_ID.text.toString()
                val typeAccess = switch1.isChecked

                presenter.request(email, senha, typeAccess)

                configLayoutParams(true)
                showLogo(true)
            }
        }
        with(switch1){
            setOnClickListener {
                if(isChecked){
                    btnSignOrRegister.text = "CADASTRAR"
                }else{
                    btnSignOrRegister.text = "ACESSAR"
                }
            }

        }
    }

    private fun configEdtEmail() {
        configLayoutParams()

        with(edtEmailLogin_ID) {

            onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            showLogo()
                        }
                    }

            setOnClickListener {
                showLogo()
            }

            layoutParams = params
        }


    }

    private fun configLayoutParams(
            modify: Boolean=false,
    ){
        this.params = LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT)

        if (modify == false) { MARGIN_TOP = 25 }

        params.setMargins(
                MARGIN_RIGHT, MARGIN_TOP,
                MARGIN_LEFT, MARGIN_BOTTOM)
    }

    private fun showLogo(show:Boolean = false){
        if(show == true){
            imageView.visibility = View.VISIBLE
        }else{
            imageView.visibility = View.GONE
        }
    }


}




