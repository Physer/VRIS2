package com.labs.valtech.vris.app.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.labs.valtech.vris.R

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
 abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
