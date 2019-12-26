package com.ashsample.androidconcepts.databinding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ashsample.androidconcepts.R



class ProductDetailsUsingDataBindingActivity : AppCompatActivity() {
@Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContentView(R.layout.databinding)
    var intent:Intent = getIntent()
    var data:DataModel = intent.extras.get("data") as DataModel;

    // DataModel d =  getIntent().getParcelableExtra("data");
    val databindingBinding:DatabindingBinding = DataBindingUtil.setContentView(this,R.layout.databinding)
    val temp = ProductData(data.androidVersion,data.androidName)
    Log.i("Ashiq","Prodcut Data::"+temp+"::"+data.androidName)
    databindingBinding.product = temp
    }

}
