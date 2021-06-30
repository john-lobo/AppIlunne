package com.johnlennonlobo.appilunne.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.johnlennonlobo.appilunne.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportActionBar?.hide()

    }
}