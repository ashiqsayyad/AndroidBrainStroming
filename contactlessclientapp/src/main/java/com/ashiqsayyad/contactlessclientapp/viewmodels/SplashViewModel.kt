package com.ashiqsayyad.contactlessclientapp.viewmodels

import android.app.Application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) :BaseViewModel(application) {

    override fun loadInitials(){
        // implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
        viewModelScope.launch {
            delay(2000)
            init_done.value= true
        }
    }

}