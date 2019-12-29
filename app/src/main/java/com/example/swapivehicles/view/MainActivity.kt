package com.example.swapivehicles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swapivehicles.R
import com.example.swapivehicles.adapter.VehicleAdapter
import com.example.swapivehicles.di.DaggerApiComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
