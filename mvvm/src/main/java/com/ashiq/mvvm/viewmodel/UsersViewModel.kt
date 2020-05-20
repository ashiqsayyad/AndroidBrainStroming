package com.ashiq.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashiq.mvvm.pojo.User
import com.ashiq.mvvm.repository.ResponseCallback
import com.ashiq.mvvm.repository.UserRepository

class UsersViewModel : ViewModel(),ResponseCallback {
    override fun onSuccess(T: Any) {
      users = T as List<User>
    }

    override fun onFail() {
        users = null
    }

    // val users:LiveData<List<User>> = MutableLiveData<List<User>>()
    var userRepo = UserRepository()//by lazy {UserRepository()  }

    var users:List<User>?=null

    fun getUsers(){
        userRepo.getUsers(this)

    }

}