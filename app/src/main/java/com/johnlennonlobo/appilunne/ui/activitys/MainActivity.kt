package com.johnlennonlobo.appilunne.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.johnlennonlobo.appilunne.R
import com.johnlennonlobo.appilunne.ui.fragments.homeFragment.HomeFragment
import com.johnlennonlobo.appilunne.ui.fragments.loginFragment.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Thread.sleep(2000)
        setTheme(R.style.Theme_AppIlunne)

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportActionBar?.hide()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
        }
    }
}