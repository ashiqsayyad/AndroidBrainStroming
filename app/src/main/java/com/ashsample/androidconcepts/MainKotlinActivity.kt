package com.ashsample.androidconcepts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashsample.androidconcepts.databinding.ProductDetailsUsingDataBindingActivity
import com.ashsample.androidconcepts.mvvm.databinding.SampleViewModel

import kotlinx.android.synthetic.main.activity_main_kotlin.*
import kotlinx.android.synthetic.main.content_main_kotlin.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

//https://github.com/irontec/android-kotlin-samples/blob/master/KotlinTest/app/src/main/java/com/irontec/examples/kotlintest/HttpUrlConnectionAsyncActivity.kt
class MainKotlinActivity : AppCompatActivity() {

   var demoViewModel:SampleViewModel?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        var butn = findViewById<Button>(R.id.coroutine);
        butn.setOnClickListener{
            CoroutineScope(IO).launch {
                fetchUsers();
            }
        }

        butn = findViewById<Button>(R.id.databinding);
        butn.setOnClickListener{
            var  intent : Intent = Intent(this,ProductDetailsUsingDataBindingActivity::class.java)
             startActivity(intent);
        }
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
           // see the object application context
            Toast.makeText(applicationContext,"Kotlin toast",Toast.LENGTH_LONG).show()
            demoViewModel!!.buttonClicked()



        }
        //https://kotlinlang.org/docs/reference/nested-classes.html
        demoViewModel  =  ViewModelProviders.of(this).get(SampleViewModel::class.java)
        demoViewModel!!.getObservableText().observe(this, Observer { text ->
            text?.let {
                butn.setText(text);
            }

        })



    }

   /* fun suspendableLambda(block: suspend () -> Unit) =refreshTitle();
   *//* fun onButtonClick(v: View){
        when(v.id){
             R.id.coroutine -> {
                 Toast.makeText(applicationContext,"coroutine is cliked",Toast.LENGTH_LONG).show()
                 refreshTitle()
             };

        }
    }*/
    suspend fun refreshTitle() {
        withContext(Dispatchers.IO) {
           fetchUsers()
        }
    }

    suspend  fun  fetchUsers(){
        var url = URL("https://api.github.com/users/ashiqsayyad")
       var urlCon = url.openConnection() as HttpURLConnection;
        if(urlCon.responseCode == HttpURLConnection.HTTP_OK) {

           try {
               val input: BufferedInputStream = BufferedInputStream(urlCon.inputStream)
               // var bufferReader: BufferedReader = BufferedReader(InputStreamReader(input));
               val data: String = readStream(input)
               Log.i("Ashiq","Response came::"+data)
           } catch(e:Exception ){
              // Toast.makeText(,"error ==="+e,Toast.LENGTH_SHORT).show()
               Log.i("Ashiq","error came::"+e.toString())
           }finally {
               urlCon.disconnect();
           }

        }else{
            //Toast.makeText(applicationContext,"Problem n fetching response from server"+urlCon.responseCode,Toast.LENGTH_SHORT).show()
        }



    }
    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

}
