package com.ashiq.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashiq.mvvm.viewmodel.UsersViewModel

class HomeActivity : AppCompatActivity() {

  var viewModel :ViewModel?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Toast.makeText(baseContext,"I am clicked",Toast.LENGTH_LONG).show()

        }
    }
}

