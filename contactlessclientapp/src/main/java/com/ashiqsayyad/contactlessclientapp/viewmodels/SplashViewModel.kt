package com.ashiqsayyad.contactlessclientapp.viewmodels

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel :BaseViewModel() {

    override fun loadInitials(){
        // implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
        viewModelScope.launch {
            delay(2000)
            init_done.value= true
        }
    }

}