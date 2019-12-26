package com.ashsample.androidconcepts.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ContextRegisteredReceiver extends BroadcastReceiver {
    //https://developer.android.com/guide/components/broadcasts
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"ContextRegisteredReceiver called"+intent.getAction(),Toast.LENGTH_SHORT).show();
    }
}
