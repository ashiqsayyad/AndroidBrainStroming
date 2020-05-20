package com.example.remoteserviceclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remoteservice_calculator.IRemote;

import java.util.List;

//https://developer.samsung.com/galaxy/others/services-with-aidl-in-android

public class RemoteServiceClientActivity extends Activity implements View.OnClickListener {
    EditText mFirst,mSecond;
    Button mAdd,mSubtract,mClear;
    TextView mResultText;
    IRemote mService;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_service_client);

        mFirst = (EditText) findViewById(R.id.firstValue);
        mSecond = (EditText) findViewById(R.id.secondValue);
        mResultText = (TextView) findViewById(R.id.resultText);

        mAdd = (Button) findViewById(R.id.add);
        mSubtract = (Button) findViewById(R.id.subtract);
        mClear = (Button) findViewById(R.id.multi);

        mAdd.setOnClickListener(this);
        mSubtract.setOnClickListener(this);
        mClear.setOnClickListener(this);

        if(mService == null)
        {
           /* Intent it = new Intent();
            it.setAction("com.remote.service.CALCULATOR");
            //binding to remote service
            bindService(it, mServiceConnection, Service.BIND_AUTO_CREATE);*/

           // above error if we call directly above things java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.remote.service.CALCULATOR }
            startService("com.remote.service.CALCULATOR");
        }

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.add:
            {
                int a = Integer.parseInt(mFirst.getText().toString());
                int b = Integer.parseInt(mSecond.getText().toString());

                try {
                    mResultText.setText("Result -> Add ->"+mService.add(a,b));
                    Log.d("Ashiq", "Binding - Add operation");
                    Toast.makeText(RemoteServiceClientActivity.this, "Addition done"+mResultText.getText(), Toast.LENGTH_SHORT).show();

                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            break;
            case R.id.subtract:
            {
            }
            break;
            case R.id.multi:
            {
            }
            break;


        }
    }

    /**
     * Service connection is used to know the status of the remote service
     */
    ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            mService = null;
            Log.d("IRemote", "Binding - Service disconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            // TODO Auto-generated method stub
            mService = IRemote.Stub.asInterface((IBinder) service);
            Log.d("IRemote", "Binding is done - Service connected");
        }
    };

    protected void onDestroy() {

        super.onDestroy();
        unbindService(mServiceConnection);
    };

    public  void startService(String intentUri) {
        Intent implicitIntent = new Intent();
        implicitIntent.setAction(intentUri);
        Context context = getApplicationContext();
        Intent explicitIntent = convertImplicitIntentToExplicitIntent(implicitIntent, context);
        if(explicitIntent != null){
           // context.startService(explicitIntent);
            bindService(explicitIntent, mServiceConnection, Service.BIND_AUTO_CREATE);
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
}
