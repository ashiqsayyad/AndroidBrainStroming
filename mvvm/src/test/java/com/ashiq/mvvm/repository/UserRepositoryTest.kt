package com.ashiq.mvvm.repository

import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class UserRepositoryTest {
    val retrofitHandle:Retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();//Retrofit.Builder().baseUrl("https://jquery.org").build();.addConverterFactory(GsonConverterFactory.create())
    val retrofitHandleHTML:Retrofit = Retrofit.Builder().baseUrl("https://www.google.com").build();//Retrofit.Builder().baseUrl("https://jquery.org").build();.addConverterFactory(GsonConverterFactory.create())
    interface  RetroHTML{
        @GET("/")
        fun getHTML():Call<ResponseBody>
    }
    interface RetroInterface{
         @GET("/posts")
         fun getProjects():Call<List<Post>>
         @GET("/posts")
         fun getResponseString():Call<String>

         @GET("/posts")
         fun getResponseBody():Call<ResponseBody>
     }
      data class Post(val userId:String,val id:String,val title:String,val body:String){

      }

    var posts:List<Post>?=null
    var responseStr:String ?=null

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getProjects(){
        val concrete=retrofitHandle.create(RetroInterface::class.java)
        val tempCall = concrete.getProjects();
        val countDownLatch= CountDownLatch(1);
        tempCall.enqueue(object:Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

                countDownLatch.countDown()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
               countDownLatch.countDown()
                posts = response.body()
               // responseStr = response.body()

            }

        })
        countDownLatch.await()
        Assert.assertNotNull(posts)
    }

    @Test
    fun getResponseString(){
        val concrete=retrofitHandle.create(RetroInterface::class.java)
        val tempCall = concrete.getResponseString();
        val countDownLatch= CountDownLatch(1);
        tempCall.enqueue(object:Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {

               countDownLatch.countDown()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
               countDownLatch.countDown()
               // posts = response
                responseStr = response.body()

            }

        })
        countDownLatch.await()
       // countDownLatch.await(5000,TimeUnit.MILLISECONDS)
        Assert.assertNotNull(responseStr)
    }



    @Test
    fun getResponseBodyAsStringHTML(){
        val concrete=retrofitHandleHTML.create(RetroHTML::class.java)
        val tempCall = concrete.getHTML();
        val countDownLatch= CountDownLatch(1);
        tempCall.enqueue(object:Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                countDownLatch.countDown()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                countDownLatch.countDown()
                // posts = response
                responseStr = response.body()!!.string()

            }

        });
        countDownLatch.await()
        // countDownLatch.await(5000,TimeUnit.MILLISECONDS)
       Assert.assertNotNull(responseStr)
    }
}