package com.johnlennonlobo.appilunne.ui.activity.login

import android.app.ActionBar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.johnlennonlobo.appilunne.R
import com.johnlennonlobo.appilunne.presenter.login.LoginViewModel
import com.johnlennonlobo.appilunne.presenter.login.LoginViewModelFactory
import com.johnlennonlobo.appilunne.rest.ConfigFirebase
import com.johnlennonlobo.appilunne.ui.activity.AbstractActivity
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_BOTTOM
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_LEFT
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_RIGHT
import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : AbstractActivity() {

    private lateinit var authentication: FirebaseAuth
    private lateinit var viewModel: LoginViewModel

    //TODO implements AbstractActivity
    override fun getLayout(): Int =R.layout.login_activity
    override fun getObject() {
        supportActionBar?.hide()

        authentication = ConfigFirebase.getFirebaseAuthentication()
        viewModel=ViewModelProvider(this, LoginViewModelFactory(authentication)).get(
            LoginViewModel::class.java
        )

        signOrRegister()
        configEdts()
    }

    override fun onStart() {
        super.onStart()
        viewModel.sucessMessage.observe(this, Observer {
            showMessage(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            showMessage(it)
        })
    }

    private fun signOrRegister() {
        with(btnSignOrRegister){
            setOnClickListener {

                val email = edtEmailLogin_ID.text.toString()
                val senha = edtSenhalLogin_ID.text.toString()
                val typeAccess = switch1.isChecked

                viewModel.getAuthentication(this@LoginActivity,email, senha, typeAccess)

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

    //TODO My Functions created in this
    private fun showMessage(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
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




