package com.johnlennonlobo.appilunne.ui.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        getObject()
    }

    @LayoutRes
    abstract fun getLayout() :Int

    abstract fun getObject()

}