package com.ashsample.androidconcepts.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;
//https://developer.android.com/guide/components/bound-services.html#java

public class LocalService extends Service {
    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();
    private String TAG ="LocalService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Ashiq"+TAG,"Local Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Ashiq"+TAG,"Local Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Ashiq"+TAG,"Local Service Destroyed");
    }
}
