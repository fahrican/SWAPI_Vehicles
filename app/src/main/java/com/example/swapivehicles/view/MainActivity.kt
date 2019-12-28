package com.example.swapivehicles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swapivehicles.R
import com.example.swapivehicles.adapter.VehicleAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vehicleAdapter: VehicleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
