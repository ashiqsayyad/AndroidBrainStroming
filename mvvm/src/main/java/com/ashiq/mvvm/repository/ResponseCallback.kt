package com.ashiq.mvvm.repository

interface ResponseCallback {
    fun onSuccess(T:Any)
    fun onFail()
}
