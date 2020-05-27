package com.ashiqsayyad.contactlessclientapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open abstract class BaseViewModel : ViewModel() {

    protected val init_done = MutableLiveData<Boolean>()

    fun observeInitDone(): LiveData<Boolean> {
        return init_done
    }
    // call this method to load bare minimum stuff for activity to load and observe for init_done
    abstract fun loadInitials()


}