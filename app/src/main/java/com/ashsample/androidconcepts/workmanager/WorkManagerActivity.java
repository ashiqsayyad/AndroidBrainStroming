package com.ashsample.androidconcepts.workmanager;


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ashsample.androidconcepts.R;

public class WorkManagerActivity extends AppCompatActivity {
    //https://codelabs.developers.google.com/codelabs/android-workmanager/#10
    //https://proandroiddev.com/exploring-the-stable-android-jetpack-workmanager-82819d5d7c34
    //https://android.jlelse.eu/android-workmanager-manage-periodic-tasks-c13fa7744ebd
    public static final String MESSAGE_STATUS = "message_status";
    TextView tvStatus;
    Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tvStatus = findViewById(R.id.textView);
        btnSend = findViewById(R.id.button);

        final WorkManager mWorkManager = WorkManager.getInstance(this);
        Data toWork = new Data.Builder().putString(MESSAGE_STATUS, "Notification Sent").build();
        // Create charging constraint
        Constraints constraints = new Constraints.Builder()
                //.setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        final OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class).setInputData(toWork).setConstraints(constraints).build();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkManager.enqueue(mRequest);
            }
        });

        mWorkManager.getWorkInfoByIdLiveData(mRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {
                    WorkInfo.State state = workInfo.getState();
                    tvStatus.append(state.toString() + "\n");
                    if(state == WorkInfo.State.SUCCEEDED){
                        tvStatus.append(workInfo.getOutputData().getString(NotificationWorker.WORK_RESULT));
                    }

                }

            }
        });
    }



}