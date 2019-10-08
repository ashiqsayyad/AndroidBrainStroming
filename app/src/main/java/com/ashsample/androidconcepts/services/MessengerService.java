package com.ashsample.androidconcepts.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {
    //https://www.survivingwithandroid.com/android-bound-service-ipc-with-messenger-2/

    /**
     * Command to the service to display a message
     */
    public static final int MSG_SAY_HELLO = 1;
    public static final int TO_UPPER_CASE_RESPONSE = 2;
    public static final int CONVERT_TO_UPPERCASE = 3;

    /**
     * Handler of incoming messages from clients.
     */
    static class IncomingHandler extends Handler {
        private Context applicationContext;

        IncomingHandler(Context context) {
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(applicationContext, "hello! I am Messenger service ready to serve you", Toast.LENGTH_SHORT).show();
                    break;
                case CONVERT_TO_UPPERCASE :
                   try {
                       String data = msg.getData().getString("data");
                       Message resp = Message.obtain(null, TO_UPPER_CASE_RESPONSE);
                       Bundle bResp = new Bundle();
                       bResp.putString("respData", data.toUpperCase());
                       resp.setData(bResp);

                       msg.replyTo.send(resp);
                   }catch(Exception e){

                   }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    Messenger mMessenger;

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }
}
