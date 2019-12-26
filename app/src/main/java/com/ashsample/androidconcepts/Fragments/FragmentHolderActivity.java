package com.ashsample.androidconcepts.Fragments;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.ashsample.androidconcepts.R;

//https://www.vogella.com/tutorials/AndroidFragments/article.html#adding-fragments-statically-to-the-layout-file

public class FragmentHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Ashiq","onCreate============Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder);

    }

    @Override
    protected void onResume() {
        Log.i("Ashiq","onResume=============Activity");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.i("Ashiq","onStop=============Activity");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.i("Ashiq","onStart=============Activity");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i("Ashiq","onRestart=============Activity");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("Ashiq","onPause=============Activity");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.i("Ashiq","onDestroy=============Activity");
        super.onDestroy();
    }

    public void onClickBtn(View v){
        DetailedFragment fragment;
        switch(v.getId()){
            case R.id.btn1 :
                 fragment = DetailedFragment.newInstance("Button One Clicked");
                getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).commit();
               // getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).addToBackStack(null).commit();


                break;
            case R.id.btn2:
                 fragment = DetailedFragment.newInstance("Button two Clicked");
                getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).addToBackStack(null).commit();


        }
    }


}
