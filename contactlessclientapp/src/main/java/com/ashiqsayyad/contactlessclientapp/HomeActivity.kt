package com.ashiqsayyad.contactlessclientapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ashiqsayyad.contactlessclientapp.databinding.ActivityHomeBinding
import com.ashiqsayyad.contactlessclientapp.ui.BaseActivity
import com.ashiqsayyad.contactlessclientapp.viewmodels.HomeViewModel

//https://android.jlelse.eu/the-complete-android-splash-screen-guide-c7db82bce565
class HomeActivity : BaseActivity() {

    val homeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.observableText.observe(this,Observer<String>{
            Toast.makeText(applicationContext,it,Toast.LENGTH_LONG).show()
        })
        homeViewModel.observeInitDone().observe(this, Observer {
            when(it){
                true-> Toast.makeText(applicationContext,"InitDone",Toast.LENGTH_LONG).show()
            }


        })


       val binding:ActivityHomeBinding= DataBindingUtil.setContentView<ActivityHomeBinding>(this,R.layout.activity_home)
        binding.homeviewmodel =homeViewModel
        binding.defaultval="No Text"
       //very imp to enable refelct changes on UI
        binding.lifecycleOwner=this
        homeViewModel.loadInitials()

       // setContentView(R.layout.activity_home)

     /*  homeViewModel.observableText.observe(this,Observer<String>{
           Toast.makeText(applicationContext,it,Toast.LENGTH_LONG).show()
       })*/


    }


}