package com.example.swapivehicles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swapivehicles.R
import com.example.swapivehicles.adapter.VehicleAdapter
import com.example.swapivehicles.di.DaggerApiComponent
import com.example.swapivehicles.model.Vehicle
import com.example.swapivehicles.viewmodel.VehicleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vehicleAdapter: VehicleAdapter

    private lateinit var vehicleViewModel: VehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerApiComponent.create().inject(this)

        vehicleViewModel = ViewModelProviders.of(this).get(VehicleViewModel::class.java)

        main_swipe_refresh_layout.setOnRefreshListener {
            main_swipe_refresh_layout.isRefreshing = false
            vehicleViewModel.refresh()
        }

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = vehicleViewModel.vehicleAdapter
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observeVehicleList()
    }

    private fun observeVehicleList() {
        val vehicleListLD: LiveData<List<Vehicle>> = vehicleViewModel.vehicleListMLD
        vehicleListLD.observe(this, Observer { allVehicles ->
            allVehicles.let {
                main_recycler_view.visibility = View.VISIBLE
                vehicleViewModel.vehicleAdapter.setUpVehicles(it)
            }
        })
    }

    private fun observeInProgress() {
        val inProgressLD: LiveData<Boolean> = vehicleViewModel.inProgressMLD
        inProgressLD.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    vehicle_fetch_error.visibility = View.GONE
                    main_recycler_view.visibility = View.GONE
                    vehicle_fetch_progress.visibility = View.VISIBLE
                } else {
                    vehicle_fetch_progress.visibility = View.GONE
                }
            }
        })
    }

    private fun observeIsError() {
        val isErrorLD: LiveData<Boolean> = vehicleViewModel.isErrorMLD
        isErrorLD.observe(this, Observer { isError ->
            isError.let { vehicle_fetch_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
    }
}
