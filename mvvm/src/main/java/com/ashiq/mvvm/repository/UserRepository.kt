package com.ashiq.mvvm.repository

import com.ashiq.mvvm.pojo.User
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

 open class UserRepository {
//https://stackoverflow.com/questions/36523972/how-to-get-string-response-from-retrofit2
    //junit for retrofit
    //https://stackoverflow.com/questions/32374508/how-to-unit-test-retrofit-api-calls
    //val retrofit = Retrofit.Builder().baseUrl("http://www.google.com").build()
   /* val retrofit = Retrofit.Builder().baseUrl("https://jquery.org").build()
    var responseStr:String?= null*/

       private var retrofitHandle:Retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();//Retrofit.Builder().baseUrl("https://jquery.org").build();.addConverterFactory(GsonConverterFactory.create())
     private var retrofitHandleHTML:Retrofit = Retrofit.Builder().baseUrl("https://www.google.com").build();//Retrofit.Builder().baseUrl("https://jquery.org").build();.addConverterFactory(GsonConverterFactory.create())
    interface  RetroHTML{
        @GET("/")
        fun getHTML():Call<ResponseBody>
    }
    interface RetroInterface{
        @GET("/posts")
        fun getUsers():Call<List<com.ashiq.mvvm.pojo.User>>
        @GET("/posts")
        fun getResponseString():Call<String>

        @GET("/posts")
        fun getResponseBody():Call<ResponseBody>
    }

    interface Google{
        @GET("/projects")
        fun getGoogleHome(): Call<ResponseBody>;

    }



    open fun getUsers( callback:ResponseCallback){
        var concrete = retrofitHandle.create(RetroInterface::class.java)
        var users = concrete.getUsers()
       users.enqueue(object :Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onFail()

            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
              callback.onSuccess(response.body()!!)
            }

        });


    }




}