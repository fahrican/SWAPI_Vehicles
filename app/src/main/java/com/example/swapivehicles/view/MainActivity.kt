package com.example.swapivehicles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swapivehicles.R
import com.example.swapivehicles.viewmodel.VehicleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vehicleViewModel: VehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vehicleViewModel = ViewModelProviders.of(this).get(VehicleViewModel::class.java)
        vehicleViewModel.refresh()

        main_swipe_refresh_layout.setOnRefreshListener {
            main_swipe_refresh_layout.isRefreshing = false
            vehicleViewModel.refresh()
        }

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = vehicleViewModel.vehicleAdapter
        }

        observeViewModelProperties()
    }

    private fun observeViewModelProperties() {
        observeVehicleList()
        observeInProgress()
        observeIsError()
    }

    private fun observeVehicleList() {
        vehicleViewModel.vehicleListMLD.observe(this, Observer { allVehicles ->
            allVehicles.let {
                main_recycler_view.visibility = View.VISIBLE
                vehicleViewModel.vehicleAdapter.setUpVehicles(it)
            }
        })
    }

    private fun observeInProgress() {
        vehicleViewModel.inProgressMLD.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    vehicle_fetch_progress.visibility = View.VISIBLE
                    vehicle_fetch_error.visibility = View.GONE
                    main_recycler_view.visibility = View.GONE
                } else {
                    vehicle_fetch_progress.visibility = View.GONE
                }
            }
        })
    }

    private fun observeIsError() {
        vehicleViewModel.isErrorMLD.observe(this, Observer { isError ->
            isError.let { vehicle_fetch_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
    }
}
