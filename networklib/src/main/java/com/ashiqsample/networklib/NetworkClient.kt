package com.ashiqsample.networklib

import android.util.ArrayMap
import com.ashiqsample.networklib.response.BaseRawResponse

interface NetworkClient {
     fun doGet(url:String,query:ArrayMap<String,String>?,headers:ArrayMap<String,String>?):BaseRawResponse
     enum class CustomErrorCode(val errcode:Int){
         UNKNOWNEX(1000),
         NW_ONMAINTHRD_EX(1001)


     }
}