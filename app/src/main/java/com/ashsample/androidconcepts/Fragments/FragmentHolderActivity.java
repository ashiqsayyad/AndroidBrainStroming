package com.ashsample.androidconcepts.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ashsample.androidconcepts.MainActivity;
import com.ashsample.androidconcepts.R;
import com.ashsample.androidconcepts.kotlinrecycler.KRecycleViewActivity;

//https://www.vogella.com/tutorials/AndroidFragments/article.html#adding-fragments-statically-to-the-layout-file

public class FragmentHolderActivity extends AppCompatActivity {
    private DemoViewModel demoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Ashiq","onCreate============Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder);
        demoViewModel = ViewModelProviders.of(this).get(DemoViewModel.class);
        Log.i("Ashiq","view Model in activity::"+demoViewModel);
        demoViewModel.getResponse().observe(this, new Observer<DemoViewModel.Response>() {
            @Override
            public void onChanged(DemoViewModel.Response response) {
                Toast.makeText( FragmentHolderActivity.this,"Service Status==="+response.isSuccess(),Toast.LENGTH_SHORT).show();
                //demoViewModel.resetData();
            }
        });
    }

    @Override
    protected void onResume() {
        Log.i("Ashiq","onResume=============Activity"+ Process.myPid());
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
               // demoViewModel.startPrinting();
                //startActivity(new Intent(this, KRecycleViewActivity.class));
                fragment = DetailedFragment.newInstance("Button One Clicked");
                //getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).commit();
             getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).addToBackStack(null).commit();


                break;
            case R.id.btn2:
              //  startActivity(new Intent(this, KRecycleViewActivity.class));
                //finish();
                fragment = DetailedFragment.newInstance("Button two Clicked");
              //getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).commit();
               getSupportFragmentManager().beginTransaction().replace(R.id.detailscontainer,fragment).addToBackStack(null).commit();


        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("Ashiq","onBackPressed=============Activity");
    }

}
