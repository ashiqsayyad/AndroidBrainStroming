package com.ashsample.androidconcepts.Fragments;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ashsample.androidconcepts.MainActivity;
import com.ashsample.androidconcepts.services.MessengerService;
import com.example.remoteservice_calculator.IRemote;

import java.util.List;

public class DemoViewModel extends AndroidViewModel {
    private static final String  TAG = "Ashiq";
    private MutableLiveData<Response> response = new MutableLiveData<>();
    IRemote mService;

    public class Response{
        public boolean isSuccess() {
            return isSuccess;
        }

        private boolean isSuccess;
        private Response(boolean isSucess){
            this.isSuccess = isSucess;
        }

    }

    private ServiceConnection mConnectionMessneger = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = IRemote.Stub.asInterface((IBinder) service);
            Log.d(TAG, "DemoViewModel Binding is done - Service connected");

            doSomeWork();


        }


        public void onServiceDisconnected(ComponentName className) {

        }
    };
    public DemoViewModel(@NonNull Application application) {
        super(application);
    }

    public void startPrinting(){
        //Bind to messenger service
       /* Intent intent = new Intent(getApplication(), MessengerService.class);
        getApplication().bindService(intent, mConnectionMessneger, Context.BIND_AUTO_CREATE);*/
        startService("com.remote.service.CALCULATOR");

    }

    private void doSomeWork(){
        Log.i(TAG,"doSomeWork in demoviewmodel");

         try {int result = mService.add(3,7);
             Log.i(TAG,"result service in demoviewmodel"+ result);
             response.postValue(new Response(true));

         }catch (Exception e){
             Log.i(TAG,"Exception connection service in demoviewmodel"+e.toString());
         }


       /* Log.i(TAG,"Thread in service==="+Thread.currentThread()+"::"+ Process.myPid());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"one more Thread==="+Thread.currentThread()+"::"+Process.myPid());
                for(int i=0;i<10;i++){
                     try{
                    Thread.sleep(1000);}catch(Exception e){}
                }
                response.postValue(new Response(true));
            }
        }).start();*/



    }

    public LiveData<Response> getResponse(){
        return response;
    }

    public void resetData(){
        response = new MutableLiveData<>();
    }

    public  void startService(String intentUri) {
        Intent implicitIntent = new Intent();
        implicitIntent.setAction(intentUri);
        Context context = getApplication();
        Intent explicitIntent = convertImplicitIntentToExplicitIntent(implicitIntent, context);
        if(explicitIntent != null){
            // context.startService(explicitIntent);
           getApplication().bindService(explicitIntent, mConnectionMessneger, Service.BIND_AUTO_CREATE);
        }
    }

    private static Intent convertImplicitIntentToExplicitIntent(Intent implicitIntent, Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    @Override
    protected void onCleared() {
        Log.i(TAG,"onCleared in demoviewmodel");
        super.onCleared();
    }
}
