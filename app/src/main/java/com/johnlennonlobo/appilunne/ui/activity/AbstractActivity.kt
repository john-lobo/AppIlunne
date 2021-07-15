package com.johnlennonlobo.appilunne.ui.activity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth
import com.johnlennonlobo.appilunne.network.ConfigFirebase

abstract class AbstractActivity: AppCompatActivity(), BasicActions {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout().root)

        //TODO é necessario iniciar a autenticação em algum onCreate pelo menos uma vez,
        // para poder usar ele posteriormente
        ConfigFirebase.getFirebaseAuthentication()
        getObject()

    }

    abstract fun getLayout() :ViewBinding

    abstract fun getObject()

    override fun showMessage(message: String) {
        // TODO alert esta vazando janela
//        AlertDialog.Builder(this)
//            .setMessage(message)
//            .setPositiveButton("OK") { _, _ ->
//                // User clicked OK button
//            }.show()

        Toast.makeText(
            this, message,
            Toast.LENGTH_SHORT
        ).show()
    }

    //esconderTeclado
    override fun View.hideKeyBoard() {
        val hideKeyBoard=getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        hideKeyBoard.hideSoftInputFromWindow(windowToken, 0)
    }




}