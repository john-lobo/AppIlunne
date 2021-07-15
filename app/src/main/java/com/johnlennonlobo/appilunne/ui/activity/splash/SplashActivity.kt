package com.johnlennonlobo.appilunne.ui.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.johnlennonlobo.appilunne.R
import com.johnlennonlobo.appilunne.ui.activity.login.AuthActivity

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
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}