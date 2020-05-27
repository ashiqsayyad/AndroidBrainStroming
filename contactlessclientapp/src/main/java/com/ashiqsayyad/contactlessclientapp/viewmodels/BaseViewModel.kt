package com.ashiqsayyad.contactlessclientapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open abstract class BaseViewModel( application: Application) : AndroidViewModel(application) {

    protected val init_done = MutableLiveData<Boolean>()

    fun observeInitDone(): LiveData<Boolean> {
        return init_done
    }
    // call this method to load bare minimum stuff for activity to load and observe for init_done
    abstract fun loadInitials()


}