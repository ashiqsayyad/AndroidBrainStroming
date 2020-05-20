package com.example.kotlinremoteservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast

private const val MSG_SAY_HELLO = 1
class MessengerRemoteService : Service() {

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private lateinit var mMessenger: Messenger


    override fun onBind(intent: Intent?): IBinder? {

       Log.i("Ashiq","MessengerRemoteService onBind")
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }

    /**
     * Handler of incoming messages from clients.
     */
    internal class IncomingHandler(
            context: Context,
            private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {

            when (msg.what) {
                MSG_SAY_HELLO ->
                    Toast.makeText(applicationContext, "hello!", Toast.LENGTH_SHORT).show()
                else -> super.handleMessage(msg)
            }
        }
    }



}