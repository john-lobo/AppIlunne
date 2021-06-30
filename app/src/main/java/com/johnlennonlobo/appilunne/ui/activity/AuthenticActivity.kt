package com.johnlennonlobo.appilunne.ui.activity

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.johnlennonlobo.appilunne.R
import kotlinx.android.synthetic.main.authentic_activity.*


class AuthenticActivity : AppCompatActivity() {
    private lateinit var params: LinearLayout.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentic_activity)
        supportActionBar?.hide()
        params = LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        editLoginFocus()

        edtEmailLogin_ID.setOnClickListener {
          setMargin(params)
        }

        btnSignOrRegister.setOnClickListener {
            setMargin(params,false)
        }

    }

    private fun editLoginFocus() {
        edtEmailLogin_ID.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    setMargin(params)
                }
            }

        }
    }

    private fun setMargin(params: LinearLayout.LayoutParams, invisible :Boolean = true) {
        val marginRL = 70
        var marginTop = 300

        if(invisible){
            imageView.visibility = View.GONE
        }else{
            imageView.visibility = View.VISIBLE
            marginTop = 25
        }

        params.setMargins(marginRL, marginTop, marginRL, 0)
        edtEmailLogin_ID.setLayoutParams(params)
    }
}