package com.ashsample.androidconcepts

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashsample.androidconcepts.databinding.DataModel
import com.ashsample.androidconcepts.databinding.ProductDetailsUsingDataBindingActivity
import com.ashsample.androidconcepts.mvvm.User
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
import java.util.*

//https://github.com/irontec/android-kotlin-samples/blob/master/KotlinTest/app/src/main/java/com/irontec/examples/kotlintest/HttpUrlConnectionAsyncActivity.kt
class MainKotlinActivity : AppCompatActivity() {

    /** Messenger for communicating with the service.  */
    private var mService: Messenger? = null
    /** Flag indicating whether we have called bind on the service.  */
    private var bound: Boolean = false

    /**
     * Class for interacting with the main interface of the service.
     */
    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mService = Messenger(service)
            bound = true
            Toast.makeText(applicationContext,"Kotlin remote servie bound",Toast.LENGTH_LONG).show()
        }

        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null
            bound = false
        }
    }

   var demoViewModel:SampleViewModel?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        var butn = findViewById<Button>(R.id.coroutine);
        butn.setOnClickListener{
            /*CoroutineScope(IO).launch {
                fetchUsers();
            }*/


        }

        butn = findViewById<Button>(R.id.databinding);
        butn.setOnClickListener{
            var  intent : Intent = Intent(this,ProductDetailsUsingDataBindingActivity::class.java)
            var dataModel:DataModel = DataModel("Android Q","10");
            intent.putExtra("data",dataModel)
             startActivity(intent);



        }

        butn = findViewById(R.id.rem_bind)
        butn.setOnClickListener{view -> startService("com.remote.kotlin.service")}

        butn = findViewById(R.id.rem_hello)
        butn.setOnClickListener{view -> val msg: Message = Message.obtain(null, 1, 0, 0)
            try {
                mService?.send(msg)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
           // see the object application context



           demoViewModel!!.getPosts()







        }
        //https://kotlinlang.org/docs/reference/nested-classes.html
        demoViewModel  =  ViewModelProviders.of(this).get(SampleViewModel::class.java)
        demoViewModel!!.getObservableText().observe(this, Observer { text ->

            text?.let {
                butn.setText(text);
            }

        })

        val observer = Observer<List<User>> {
            Toast.makeText(applicationContext,"data came",Toast.LENGTH_LONG).show()
        }
       // demoViewModel!!.getUsers().observe(this,observer)

        demoViewModel!!.getUsersRaw().observe(this, Observer { text-> Toast.makeText(applicationContext,"http code"+text,Toast.LENGTH_LONG).show()
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

    fun startService(intentUri: String) {
        val implicitIntent = Intent()
        implicitIntent.action = intentUri
        val explicitIntent = convertImplicitIntentToExplicitIntent(implicitIntent, applicationContext)
        if (explicitIntent != null) {
            applicationContext.bindService(explicitIntent, mConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private fun convertImplicitIntentToExplicitIntent(implicitIntent: Intent, context: Context): Intent? {
        val pm = context.packageManager
        val resolveInfoList = pm.queryIntentServices(implicitIntent, 0)

        if (resolveInfoList == null || resolveInfoList.size != 1) {
            return null
        }
        val serviceInfo = resolveInfoList[0]
        val component = ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name)
        val explicitIntent = Intent(implicitIntent)
        explicitIntent.component = component
        return explicitIntent
    }
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
