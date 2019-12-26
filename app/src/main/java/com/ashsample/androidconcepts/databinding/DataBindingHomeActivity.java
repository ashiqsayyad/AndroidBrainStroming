package com.ashsample.androidconcepts.databinding;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.View;

import com.ashsample.androidconcepts.R;

import java.util.ArrayList;
import java.util.List;

public class DataBindingHomeActivity extends AppCompatActivity {
    DbactivityMainBinding dbactivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbactivity_main);
        dbactivityMainBinding = DataBindingUtil.setContentView(this, R.layout.dbactivity_main);
        populateData();

    }

    private void populateData() {
        List<DataModel> dataModelList = new ArrayList<>();

        dataModelList.add(new DataModel("Android Oreo", "8.1"));
        dataModelList.add(new DataModel("Android Pie", "9.0"));
        dataModelList.add(new DataModel("Android Nougat", "7.0"));
        dataModelList.add(new DataModel("Android Marshmallow", "6.0"));

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(dataModelList, this);
        dbactivityMainBinding.setMyAdapter(myRecyclerViewAdapter);
    }

}
