package com.johnlennonlobo.appilunne.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.johnlennonlobo.appilunne.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper())
                .postDelayed({
                    openAuthentic()
                }
                        ,3000)

    }

    private fun openAuthentic() {
        startActivity(Intent(this,AuthenticActivity::class.java))
        finish()

    }
}