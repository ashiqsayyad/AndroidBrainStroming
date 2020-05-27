package com.ashiqsayyad.contactlessclientapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ashiqsayyad.contactlessclientapp.HomeActivity
import com.ashiqsayyad.contactlessclientapp.R
import com.ashiqsayyad.contactlessclientapp.viewmodels.SplashViewModel


class SplashActivity : AppCompatActivity() {
    val splashViewModel by lazy { ViewModelProvider(this).get(SplashViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel.loadInitials()
        splashViewModel.observeInitDone().observe(this, Observer {
            navigateToHomeScreen()
        })


    }
    private fun navigateToHomeScreen(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }


}
