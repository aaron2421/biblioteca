package com.airasoft.biblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splash.setKeepOnScreenCondition { true }
        val intent = Intent(this, BookStoreActivity::class.java)
        startActivity(intent)
        finish()

        //buenas tardes
    }
}