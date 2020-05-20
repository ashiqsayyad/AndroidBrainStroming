package com.ashiqsample.networklib

import android.os.NetworkOnMainThreadException
import android.util.ArrayMap
import com.ashiqsample.networklib.response.BaseRawResponse
import com.ashiqsample.networklib.response.ErrorRawResponse
import com.ashiqsample.networklib.response.SuccessRawResponse
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NativeNetworkClient:NetworkClient {
    override fun doGet(url: String, query: ArrayMap<String, String>?, headers: ArrayMap<String, String>?):BaseRawResponse {
         val uri:URL
        var urlCon:HttpURLConnection?= null
        var input: BufferedInputStream ?= null
        return try{
            uri = URL(url)
            urlCon= uri.openConnection() as HttpURLConnection;
            input = BufferedInputStream(urlCon.inputStream)
            SuccessRawResponse(readStream(input),urlCon.responseCode)

        }catch (ex:Exception){

            var errorCode = NetworkClient.CustomErrorCode.UNKNOWNEX.errcode

            when(ex){
                is NetworkOnMainThreadException -> errorCode =NetworkClient.CustomErrorCode.NW_ONMAINTHRD_EX.errcode
            }
            ErrorRawResponse(ex.toString(), errorCode)

        }finally {
            input?.close()
         urlCon?.disconnect()
        }

    }

    private fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

}