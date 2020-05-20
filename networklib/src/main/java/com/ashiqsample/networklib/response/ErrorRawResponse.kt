package com.ashiqsample.networklib.response

class ErrorRawResponse(val exception:String,httpOrExceptionCode:Int):BaseRawResponse(httpOrExceptionCode) {
}