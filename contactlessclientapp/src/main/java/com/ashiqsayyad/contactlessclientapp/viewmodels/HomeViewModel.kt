package com.ashiqsayyad.contactlessclientapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
//https://www.bignerdranch.com/blog/two-way-data-binding-on-android-observing-your-view-with-xml/
//https://android.jlelse.eu/the-complete-android-splash-screen-guide-c7db82bce565

class HomeViewModel: BaseViewModel() {
    val observableText = MutableLiveData("AshiqIntial")
    val _observableText:LiveData<String> = observableText

   /* init {
        observableText = MutableLiveData<String>()
        observableText.value="Initial Value"

    }*/

    override fun loadInitials(){
        // implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
        viewModelScope.launch {
            delay(2000)
            init_done.value= true
        }
    }


    public fun changeText(){
        observableText.postValue( Random().nextInt().toString())
    }



}