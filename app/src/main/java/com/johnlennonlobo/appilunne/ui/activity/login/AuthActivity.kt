package com.johnlennonlobo.appilunne.ui.activity.login

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth
import com.johnlennonlobo.appilunne.databinding.AuthActivityBinding

import com.johnlennonlobo.appilunne.model.Usuario
import com.johnlennonlobo.appilunne.network.ConfigFirebase
import com.johnlennonlobo.appilunne.ui.activity.AbstractActivity
import com.johnlennonlobo.appilunne.ui.activity.mainHome.MainHomeActivity


class AuthActivity : AbstractActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var usuario: Usuario
    private lateinit var binding: AuthActivityBinding

    //TODO implements AbstractActivity
    override fun getLayout(): ViewBinding {
       binding = AuthActivityBinding.inflate(layoutInflater)
        return binding
    }

    override fun getObject() {
      //  authentication = ConfigFirebase.getFirebaseAuthentication()
       // authentication.signOut() // deslogar usuario
       // verifyUsuLog()

        supportActionBar?.hide()
        actionViews()

        viewModel=ViewModelProvider(this, AuthViewModelFactory()).get(
            AuthViewModel::class.java
        )
        with(viewModel){
            sucessMessage.observe(this@AuthActivity, Observer {
                AlertDialog.Builder(this@AuthActivity)
                    .setTitle("Sucesso")
                    .setMessage(it)
                    .setPositiveButton("OK") { _, _ ->
                        openMainHome()
                    }.show()
            })
            errorMessage.observe(this@AuthActivity, Observer {
                showMessage(it)
            })
        }
    }

    private fun actionViews() {

        with(binding.btnSignOrRegister) {
            setOnClickListener {
                hideKeyBoard()
                hideLogo(false)

                val nome = binding.edtNomeLoginID.text.toString()
                val email=binding.edtEmailLoginID.text.toString()
                val senha=binding.edtSenhalLoginID.text.toString()
                val typeAccess= binding.selectTypeAccessID.isChecked

                usuario=Usuario()
                usuario.nome=nome
                usuario.email=email
                usuario.senha=senha

                with(viewModel) {
                    getAuthentication(typeAccess, usuario)

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
                        binding.edtNomeLoginID.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.btnSignOrRegister.text="ACESSAR"
                        binding.edtNomeLoginID.visibility = View.GONE
                    }
                }
            }
        }

        with(binding.edtNomeLoginID) {
            setOnClickListener {
                hideLogo(true)
            }
            onFocusChangeListener=View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    hideLogo(true)
                }
            }
        }

        with(binding.edtEmailLoginID) {
            setOnClickListener {
                hideLogo(true)
            }
            onFocusChangeListener=View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    hideLogo(true)
                }
            }
        }

        with(binding.edtSenhalLoginID) {
            setOnClickListener {
                hideLogo(true)
            }
            onFocusChangeListener=View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    hideLogo(true)
                }
            }
        }
    }

    //TODO My Functions created in this

    private fun openMainHome() {
        val intent=Intent(
            this@AuthActivity.applicationContext, MainHomeActivity
            ::class.java
        )
        this@AuthActivity.startActivity(intent)
        finish()
    }

    //    fun verifyUsuLog(){
//        val user: FirebaseUser? = authentication.currentUser
//        if(user != null){
//            openMainHome()
//        }
//    }

    private fun hideLogo(show: Boolean=true) {
        if (show) {
            binding.imageLogoID.visibility=View.GONE
        } else {
            binding.imageLogoID.visibility=View.VISIBLE
        }
    }

//    private fun configLayoutParams(modify: Boolean): LinearLayout.LayoutParams {
//        var marginTop: Int
//        val params: LinearLayout.LayoutParams=LinearLayout.LayoutParams(
//            ActionBar.LayoutParams.MATCH_PARENT,
//            ActionBar.LayoutParams.WRAP_CONTENT
//        )
//        when {
//            modify -> {
//                showLogo()
//                marginTop=25
//            }
//            else -> {
//                marginTop=100
//                showLogo(false)
//            }
//        }
//
//        params.setMargins(
//            MARGIN_RIGHT, marginTop,
//            MARGIN_LEFT, MARGIN_BOTTOM
//        )
//        return params
//    }
}




