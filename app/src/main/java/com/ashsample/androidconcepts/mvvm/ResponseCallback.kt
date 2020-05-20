package com.ashsample.androidconcepts.mvvm

interface ResponseCallback {
    fun onSuccess(users:List<User>)
    fun onFail()
}
