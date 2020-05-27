package com.ashiqsayyad.contactlessclientapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashiqsayyad.contactlessclientapp.commonutils.SharedPrefUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
//https://www.bignerdranch.com/blog/two-way-data-binding-on-android-observing-your-view-with-xml/
//https://android.jlelse.eu/the-complete-android-splash-screen-guide-c7db82bce565
//https://developer.android.com/topic/libraries/data-binding/expressions#expression_language
//https://stackoverflow.com/questions/9290651/make-a-hyperlink-textview-in-android
//https://learntodroid.com/how-to-create-a-hyperlink-using-android-textview/
class HomeViewModel(application: Application): BaseViewModel(application) {
    val observableText = MutableLiveData("AshiqIntial")
    val _observableText:LiveData<String> = observableText

    val showSignIn = MutableLiveData(false)
    val _showSignIn:LiveData<Boolean> = showSignIn

   /* init {
        observableText = MutableLiveData<String>()
        observableText.value="Initial Value"

    }*/

    override fun loadInitials(){
        // implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
        viewModelScope.launch {
            showSignIn.value= SharedPrefUtils.contains(getApplication(),"profile")
          //  delay(2000)
            init_done.value= true
        }
    }


    public fun changeText(){
        observableText.postValue( Random().nextInt().toString())
    }



}