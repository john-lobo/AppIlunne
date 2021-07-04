package com.johnlennonlobo.appilunne.ui.activity.login

import android.app.ActionBar
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.johnlennonlobo.appilunne.R
import com.johnlennonlobo.appilunne.model.data.AppDataSource
import com.johnlennonlobo.appilunne.helper.ConfigFirebase
import com.johnlennonlobo.appilunne.presenter.ViewHome
import com.johnlennonlobo.appilunne.presenter.login.LoginPresenter
import com.johnlennonlobo.appilunne.ui.activity.AbstractActivity
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_BOTTOM
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_LEFT
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_RIGHT
import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : AbstractActivity(), ViewHome.View {
    private lateinit var presenter: LoginPresenter
    private lateinit var authentication: FirebaseAuth

    //TODO implements AbstractActivity
    override fun getLayout(): Int =R.layout.login_activity

    override fun getObject() {
        supportActionBar?.hide()
        authentication = ConfigFirebase
                .getFirebaseAuthentication()

        val dataSource = AppDataSource(authentication, this)
        presenter = LoginPresenter(this, dataSource)
        signOrRegister()
        configEdts()

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
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun signOrRegister() {
        with(btnSignOrRegister){
            setOnClickListener {
                val email = edtEmailLogin_ID.text.toString()
                val senha = edtSenhalLogin_ID.text.toString()
                val typeAccess = switch1.isChecked

                presenter.request(email, senha, typeAccess)

                edtEmailLogin_ID.layoutParams = configLayoutParams(true)
                hideKeyBoard()

            }
        }
        with(switch1){
            setOnClickListener {

                hideKeyBoard()
                edtEmailLogin_ID.layoutParams = configLayoutParams(true)

                if(isChecked){
                    btnSignOrRegister.text = "CADASTRAR"
                }else{
                    btnSignOrRegister.text = "ACESSAR"
                }
            }

        }

    }

    private fun View.hideKeyBoard() {

        // esconder teclado
        val hideKeyBoard=getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        hideKeyBoard.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun configEdts() {

        with(edtEmailLogin_ID) {
            onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            layoutParams = configLayoutParams(false)
                        }
                    }
            setOnClickListener {
                layoutParams = configLayoutParams(false)
            }

        }

        with(edtSenhalLogin_ID) {

            onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    configLayoutParams(false)
                }
            }
            setOnClickListener {
                edtEmailLogin_ID.layoutParams = configLayoutParams(false)
            }

        }

    }

    private fun configLayoutParams(modify: Boolean): LinearLayout.LayoutParams {

        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        var marginTop: Int
        when {
            modify -> {
                showLogo()
                marginTop = 25
            }
            else -> {
                marginTop = 100
                showLogo(false)
            }
        }

        params.setMargins(
            MARGIN_RIGHT, marginTop,
            MARGIN_LEFT, MARGIN_BOTTOM
        )
        return params
    }

    private fun showLogo(show: Boolean = true){
        if(show){
            imageView.visibility = View.VISIBLE
        }else{
            imageView.visibility = View.GONE
        }
    }


}




