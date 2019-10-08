package com.ashsample.remoteservice;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

import com.ashsample.remoteservice.IRemoteService;

public class RemoteService extends Service {
    //https://developer.samsung.com/galaxy/others/services-with-aidl-in-android
    //https://developer.android.com/guide/components/aidl#Create

    //imp notes
    //in client manually create package the crate aidl with same name using android studio and then finally copy the content
    // original aidl into above aidl else it will give compilation error
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return binder;
    }

    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        public int getPid(){
            return Process.myPid();
        }
        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }

        public int add(int a, int b)
                throws RemoteException {
            // TODO Auto-generated method stub
            return (a + b);
        }

        public int subtract(int a, int b)
                throws RemoteException {
            // TODO Auto-generated method stub
            return (a - b);
        }

        public double multiply(int a, int b)
                throws RemoteException {
            // TODO Auto-generated method stub
            return (a * b);
        }
    };
}