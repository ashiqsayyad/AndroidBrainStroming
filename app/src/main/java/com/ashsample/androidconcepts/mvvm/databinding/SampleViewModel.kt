package com.ashsample.androidconcepts.mvvm.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class SampleViewModel : ViewModel() {

    var observableString = MutableLiveData<String>();

    fun getObservableText():LiveData<String> {
        return observableString;
    }

    fun buttonClicked(){
        observableString.postValue(Random().nextInt(100).toString())
    }



}