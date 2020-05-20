package com.ashsample.androidconcepts.mvvm.databinding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashsample.androidconcepts.mvvm.ResponseCallback
import com.ashsample.androidconcepts.mvvm.SampleRepo
import com.ashsample.androidconcepts.mvvm.User
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class SampleViewModel : ViewModel(),ResponseCallback {

    val repo by lazy { SampleRepo() }

    var observableString = MutableLiveData<String>()
    private val usersLiveData = MutableLiveData<List<User>>()
    private val usersRawLiveData = MutableLiveData<String>()

    fun getObservableText():LiveData<String> {
        return observableString;
    }

    fun buttonClicked(){
        observableString.postValue(Random().nextInt(100).toString())
    }
    fun getPosts(){
//https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html
        //if we dont use thread here, it will block UI thread and will get network on main thread exception
       //repo.getPosts(this)

        //use thread for non blocking call (main-safety that main thread safety for unblocking call)
        /*Thread(Runnable { val rawresponse = repo.getPostsRaw();
            usersRawLiveData.postValue(rawresponse.httpCode.toString()) }).start()*/
       //if we dont use thread here, it will block UI thread and will get network on main thread exception

        /*Thread{val rawresponse = repo.getPostsRaw()
            //if we dont usr post value app will crash saying live data can not be updated from another thread using set value
            usersRawLiveData.postValue(rawresponse.httpCode.toString()) }.start()*/


        // below piece of code will give network on main thread exception as coroutine launched is scoped to viewmodel with Dispatcher MAIN by default
/*
        viewModelScope.launch{
            Log.i("Ashiq","Current Thread for viewModelScope default dispatcher:: "+Thread.currentThread())
            val rawresponse = repo.getPostsRaw()
            usersRawLiveData.postValue(rawresponse.httpCode.toString())

        }*/

       /* launchDataLoad{
            val rawresponse = repo.getPostsRaw()
            usersRawLiveData.postValue(rawresponse.httpCode.toString())
        }*/


         //Below lines of code executes sequentially although getPostsSuspended will execute on new worker thread
        viewModelScope.launch{
            Log.i("Ashiq","Current thread viewModelScope:: "+Thread.currentThread())
            getPostsSuspended()
            Log.i("Ashiq","done=====")
        }


        //

    }

    //main safe function as its getting excecuted on another worker thread
    suspend fun getPostsSuspended(){
     withContext(IO){
         Log.i("Ashiq","Current thread viewModelScope context switched io in getPostsSuspended:: "+Thread.currentThread())
         val rawresponse = repo.getPostsRaw()
         usersRawLiveData.postValue(rawresponse.httpCode.toString())
     }
    }
    // generic function which takes lambda argument as block suspend function and execute suspend block function on dispatcher IO
    private fun launchDataLoad(block: suspend () -> Unit): Job {
        Log.i("Ashiq","Current thread launchDataLoad:: "+Thread.currentThread())
        return viewModelScope.launch(IO) {
            Log.i("Ashiq","Current thread launchDataLoad in :: viewModelScope.launch(IO) "+Thread.currentThread())
            block()

        }
    }

    override fun onSuccess(users:List<User>) {
        Log.i("Ashiq","view model onSuccess=========================")
        usersLiveData.value=users
    }

    override fun onFail() {
        Log.i("Ashiq","view model onFail=========================")
    }

    fun getUsers():LiveData<List<User>>{
        return usersLiveData;
    }

    fun getUsersRaw():LiveData<String>{
        return usersRawLiveData;
    }

    /*suspend fun refreshTitle() {
        // interact with *blocking* network and IO calls from a coroutine
        withContext(Dispatchers.IO) {
            val result = try {
                // Make network request using a blocking call
                network.fetchNextTitle().execute()
            } catch (cause: Throwable) {
                // If the network throws an exception, inform the caller
                throw TitleRefreshError("Unable to refresh title", cause)
            }

            if (result.isSuccessful) {
                // Save it to database
                titleDao.insertTitle(Title(result.body()!!))
            } else {
                // If it's not successful, inform the callback of the error
                throw TitleRefreshError("Unable to refresh title", null)
            }
        }
    }*/


}