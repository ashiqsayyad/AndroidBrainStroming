package com.ashsample.androidconcepts;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.ashsample.androidconcepts.Fragments.FragmentHolderActivity;
import com.ashsample.androidconcepts.databinding.DataBindingHomeActivity;
import com.ashsample.androidconcepts.kotlinrecycler.KRecycleViewActivity;
import com.ashsample.androidconcepts.mvvm.room.NoteDao;
import com.ashsample.androidconcepts.mvvm.room.NoteDatabase;
import com.ashsample.androidconcepts.mvvm.room.NoteEntity;
import com.ashsample.androidconcepts.receivers.ContextRegisteredReceiver;
import com.ashsample.androidconcepts.services.LocalService;
import com.ashsample.androidconcepts.services.MessengerService;
import com.ashsample.androidconcepts.workmanager.WorkManagerActivity;
import com.ashsample.remoteservice.IRemoteService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashsample.androidconcepts.recycleviews.pojos.MainRecycleViewAdapter;
import com.ashsample.androidconcepts.recycleviews.pojos.MainRecycleViewItem;
import com.ashsample.androidconcepts.recycleviews.pojos.MainRecycleViewItemsGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainRecycleViewAdapter.onClickInterface {
   //Android Architecture
    //https://developer.android.com/guide/platform

    private ArrayList<MainRecycleViewItem> listitems;
    RecyclerView.Adapter mainRecycleViewAdapter;
    RecyclerView.LayoutManager mainRecycleViewLayoutManager;
    public static final String localBroadcastAction ="com.example.broadcast.localbroadcast";

    //Local service specific
    LocalService mService;
    boolean mBound = false;
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = (LocalService) binder.getService();
            mBound = true;
            Toast.makeText(MainActivity.this,"Local Service Connected",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            Toast.makeText(MainActivity.this,"Local Service onServiceDisconnected",Toast.LENGTH_SHORT).show();
        }
    };

    //below for talking to remote service using Messenger
    /** Messenger for communicating with the service. */
    Messenger mServiceMessenger = null;

    /** Flag indicating whether we have called bind on the service. */
    boolean boundMessenger;

    /**
     * Class for interacting with the main interface of the service.
     */
    private ServiceConnection mConnectionMessneger = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mServiceMessenger = new Messenger(service);
            boundMessenger = true;
            Toast.makeText(MainActivity.this,"Messenger Service Connected",Toast.LENGTH_SHORT).show();
        }


        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mServiceMessenger = null;
            boundMessenger = false;
            Toast.makeText(MainActivity.this,"Messenger Service disConnected",Toast.LENGTH_SHORT).show();
        }
    };
  private class ResponseHandler extends Handler{
      @Override
      public void handleMessage(Message msg) {
          switch (msg.what){
              case MessengerService.TO_UPPER_CASE_RESPONSE:
                 String result = msg.getData().getString("respData");
                  Toast.makeText(MainActivity.this,"message from messenger service:::"+result,Toast.LENGTH_SHORT).show();
          }

      }
  }
    private ResponseHandler responseHandler = new ResponseHandler();

  //Remote AIDL Service
  IRemoteService iRemoteService;
  boolean ismBoundAIDL;
    /**
     * Service connection is used to know the status of the remote service
     */
    ServiceConnection mServiceConnectionAIDL = new ServiceConnection() {
///imp notes
//    //in client manually create package the crate aidl with same name using android studio and then finally copy the content
//    // original aidl into above aidl else it will give compilation error
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

            iRemoteService = null;
            Log.d("Ashiq", "IRemote ServiceBinding - Service disconnected");
            ismBoundAIDL = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            // TODO Auto-generated method stub
            iRemoteService = IRemoteService.Stub.asInterface((IBinder) service);
            Log.d("IRemote", "Binding is done - Service connected");
            ismBoundAIDL = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BroadcastReceiver br = new ContextRegisteredReceiver();


        //example of context registered broadcast for airplane mode
        /*IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, filter);

*/
        //context registered RX
       /* IntentFilter filter = new IntentFilter(localBroadcastAction);
        registerReceiver(br, filter);*/

        //local broadcast RX
         IntentFilter filter = new IntentFilter(localBroadcastAction);
        LocalBroadcastManager.getInstance(this).registerReceiver(br, filter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent temp = new Intent(MainActivity.this,MainKotlinActivity.class);
                startActivity(temp);

                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NoteDao noteDao = NoteDatabase.getInstance(MainActivity.this).getNoteDao();
                        NoteEntity temp = new NoteEntity("AshiqNote1","this is ashiq1",1);
                        noteDao.insert(temp);

                        NoteEntity temp1 = new NoteEntity("AshiqNote12","this is ashiq12",2);
                        noteDao.insert(temp1);

                        List<NoteEntity> tempList = noteDao.getAllNotes();
                       // NoteEntity temp3 = new NoteEntity("AshiqNote2","this is ashiq2",2);
                       // temp.setId(tempList.get(0).getId());


                        noteDao.deleteByDescription("this is ashiq12");
                        tempList = noteDao.getAllNotes();
                    }
                });*///.start();



            }
        });
        initMainRecylceView();
        tryMaps();


    }

    @Override
    protected void onStart() {
        super.onStart();
        //Intent intent = new Intent(this, LocalService.class);
        //bindService(intent, connection, Context.BIND_AUTO_CREATE);

        //Bind to messenger service
        // intent = new Intent(this, MessengerService.class);
        //bindService(intent, mConnectionMessneger, Context.BIND_AUTO_CREATE);
        //Bind to remote app aidl service
        Intent intent = new Intent("com.ashsample.remoteservice.IRemoteService");
         intent.setPackage("com.ashsample.remoteservice");
        Log.i("Ashiq","IRemoteService.class.getName()"+IRemoteService.class.getName());
        bindService(intent, mServiceConnectionAIDL, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onStop() {
        super.onStop();
      //  unbindService(connection);
       // mBound = false;
    }

    public void tryMaps(){
        ArrayMap<String, String> temp = new ArrayMap<>();
         temp.put("second","tiger");
        temp.put("first","zebra");
        temp.put("thrid","deer");

         HashMap<String, String> temp1 = new HashMap<>();
         temp1.put("second","tiger");
         temp1.put("first","zebra");
         temp1.put("thrid","deer");



    }


    public void initMainRecylceView(){
       listitems = MainRecycleViewItemsGenerator.getInstance().getListitems();
        mainRecycleViewAdapter = new MainRecycleViewAdapter(listitems,this);
        mainRecycleViewLayoutManager =new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mainRecycleView = findViewById(R.id.mainrecycleview);
        mainRecycleView.setAdapter(mainRecycleViewAdapter);
        mainRecycleView.setLayoutManager(mainRecycleViewLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ashiq","pppppppppppppppppp");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onclick(int position) {
        //Toast.makeText(this, "recyclerview postion::"+position, Toast.LENGTH_SHORT).show();
        switch(position){
            //Local Service
            case 2:
                if (mBound) {
                    // Call a method from the LocalService.
                    // However, if this call were something that might hang, then this request should
                    // occur in a separate thread to avoid slowing down the activity performance.
                    int num = mService.getRandomNumber();
                    Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
                    break;
                }
            case 3:
                if (boundMessenger) {
                    // Call a method from the LocalService.
                    // However, if this call were something that might hang, then this request should
                    // occur in a separate thread to avoid slowing down the activity performance.

                    try {
                        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                        mServiceMessenger.send(msg);
                        msg = Message.obtain(null, MessengerService.CONVERT_TO_UPPERCASE, 0, 0);
                        msg.replyTo = new Messenger(responseHandler);
                        Bundle b = new Bundle();
                        b.putString("data", "Convert Me To Uppere case");
                        msg.setData(b);
                        mServiceMessenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                //remote service demo
            case 4:
                try {
                    Toast.makeText(this, iRemoteService.add(2, 3), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                       Log.i("Ashiq","Excpetion in remote aidl service"+e.toString());
                }
                break;
            case 5:
                Intent i = new Intent(this, WorkManagerActivity.class);
                this.startActivity(i);
                break;
            case 6:
                Intent intent = new Intent();
                intent.setAction(localBroadcastAction);
                intent.putExtra("data","Notice me senpai!");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                //sendBroadcast(intent);
            case 7:
                Intent temp = new Intent(this, FragmentHolderActivity.class);
                this.startActivity(temp);
                break;
            case 8:
                 temp = new Intent(this, MainKotlinActivity.class);
                this.startActivity(temp);
                break;
            case 9:
                temp = new Intent(this, KRecycleViewActivity.class);
                this.startActivity(temp);
                break;
            case 10:
                temp = new Intent(this, DataBindingHomeActivity.class);
                this.startActivity(temp);
                break;
        }
    }
}
