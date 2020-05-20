package com.ashsample.androidconcepts.mvvm

import android.util.Log
import android.widget.Toast
import com.ashiqsample.networklib.NativeNetworkClient
import com.ashiqsample.networklib.NetworkClient
import com.ashiqsample.networklib.response.BaseRawResponse
import com.ashiqsample.networklib.response.SuccessRawResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class SampleRepo {

    private var retrofitHandle: Retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();//Retrofit.Builder().baseUrl("https://jquery.org").build();.addConverterFactory(GsonConverterFactory.create())
     private val nativeNwClient by lazy{NativeNetworkClient()}
    interface RetroInterface{
        @GET("/posts")
        fun getPosts(): Call<List<com.ashsample.androidconcepts.mvvm.User>>
        @GET("/posts")
        fun getResponseString(): Call<String>

        @GET("/posts")
        fun getResponseBody(): Call<ResponseBody>
    }
     fun getPosts(callback:ResponseCallback ){
      val concreteIMPL=   retrofitHandle.create(RetroInterface::class.java)
        val call = concreteIMPL.getPosts()
         call.enqueue(object: Callback<List<User>>{
             override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                 callback.onSuccess(response.body()!!)
             }

             override fun onFailure(call: Call<List<User>>, t: Throwable) {
                 callback.onFail()
             }
         })
     }

    fun getPostsRaw():BaseRawResponse{
        return  nativeNwClient.doGet("https://jsonplaceholder.typicode.com/posts",null,null)

    }
}