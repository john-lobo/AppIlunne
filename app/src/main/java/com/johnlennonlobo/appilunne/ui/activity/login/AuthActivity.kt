package com.johnlennonlobo.appilunne.ui.activity.login

import android.app.ActionBar
import android.content.Intent
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.johnlennonlobo.appilunne.databinding.AuthActivityBinding

import com.johnlennonlobo.appilunne.model.Usuario
import com.johnlennonlobo.appilunne.network.ConfigFirebase
import com.johnlennonlobo.appilunne.ui.activity.AbstractActivity
import com.johnlennonlobo.appilunne.ui.activity.mainHome.MainHomeActivity
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_BOTTOM
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_LEFT
import com.johnlennonlobo.appilunne.utils.Constants.Companion.MARGIN_RIGHT


class AuthActivity : AbstractActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var usuario: Usuario
    private lateinit var binding: AuthActivityBinding
    private lateinit var authentication: FirebaseAuth

    //TODO implements AbstractActivity
    override fun getLayout(): ViewBinding {
       binding = AuthActivityBinding.inflate(layoutInflater)
        return binding
    }

    override fun getObject() {
        authentication = ConfigFirebase.getFirebaseAuthentication()
       // authentication.signOut() // deslogar usuario
       // verifyUsuLog()

        supportActionBar?.hide()
        actionViews()

        viewModel=ViewModelProvider(this, AuthViewModelFactory()).get(
            AuthViewModel::class.java
        )
        with(viewModel){
            sucessMessage.observe(this@AuthActivity, Observer {
                showMessage(it)
            })
            errorMessage.observe(this@AuthActivity, Observer {
                showMessage(it)
            })
        }


    }

//    fun verifyUsuLog(){
//        val user: FirebaseUser? = authentication.currentUser
//        if(user != null){
//            openMainHome()
//        }
//    }

    private fun openMainHome() {
        val intent=Intent(
            this@AuthActivity.applicationContext, MainHomeActivity
            ::class.java
        )
        this@AuthActivity.startActivity(intent)
        finish()
    }

    private fun actionViews() {

        with(binding.btnSignOrRegister) {
            setOnClickListener {
                hideKeyBoard()
                binding.edtEmailLoginID.layoutParams=configLayoutParams(true)

                val email=binding.edtEmailLoginID.text.toString()
                val senha=binding.edtSenhalLoginID.text.toString()
                val typeAccess= binding.selectTypeAccessID.isChecked

                usuario=Usuario()
                usuario.email=email
                usuario.senha=senha

                with(viewModel) {
                    getAuthentication(typeAccess, usuario)

                    loginSuccess.observe(this@AuthActivity, Observer {
                        if (it) {
                            openMainHome()
                        }
                    })

                    hideProgressBar.observe(this@AuthActivity, Observer {
                        if (it) {
                            binding.progressBarLoginID.visibility=View.INVISIBLE
                        }else{
                            binding.progressBarLoginID.visibility=View.VISIBLE
                        }
                    })

                }

            }
        }

        with(binding.selectTypeAccessID) {
            setOnCheckedChangeListener { _, isChecked ->
                hideKeyBoard()
                when {
                    isChecked -> {
                        binding.btnSignOrRegister.text="CADASTRAR"
                    }
                    else -> {
                        binding.btnSignOrRegister.text="ACESSAR"
                    }
                }
            }

        }

        with(binding.edtEmailLoginID) {
            setOnClickListener {
                layoutParams=configLayoutParams(false)
            }
            onFocusChangeListener=View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    layoutParams=configLayoutParams(false)
                }
            }
        }

        with(binding.edtSenhalLoginID) {
            setOnClickListener {
                binding.edtEmailLoginID.layoutParams=configLayoutParams(false)
            }
            onFocusChangeListener=View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    configLayoutParams(false)
                }
            }
        }
    }

    //TODO My Functions created in this
    private fun configLayoutParams(modify: Boolean): LinearLayout.LayoutParams {
        var marginTop: Int
        val params: LinearLayout.LayoutParams=LinearLayout.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        when {
            modify -> {
                showLogo()
                marginTop=25
            }
            else -> {
                marginTop=100
                showLogo(false)
            }
        }

        params.setMargins(
            MARGIN_RIGHT, marginTop,
            MARGIN_LEFT, MARGIN_BOTTOM
        )
        return params
    }

    private fun showLogo(show: Boolean=true) {
        if (show) {
            binding.imageLogoID.visibility=View.VISIBLE
        } else {
            binding.imageLogoID.visibility=View.GONE
        }
    }
}




