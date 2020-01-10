package com.example.swapivehicles.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swapivehicles.adapter.VehicleAdapter
import com.example.swapivehicles.di.DaggerApiComponent
import com.example.swapivehicles.model.Vehicle
import com.example.swapivehicles.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VehicleViewModel : ViewModel() {

    @Inject
    lateinit var vehicleAdapter: VehicleAdapter
    @Inject
    lateinit var networkService: NetworkService
    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var vehicleListMLD: MutableLiveData<List<Vehicle>>
    @Inject
    lateinit var inProgressMLD: MutableLiveData<Boolean>
    @Inject
    lateinit var isErrorMLD: MutableLiveData<Boolean>

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun refresh() {
        fetchVehicles()
    }

    private fun fetchVehicles() {
        compositeDisposable.add( // API call get stored in compositeDisposable
            networkService.fetchVehicle() // Makes the call to the endpoint
                .subscribeOn(Schedulers.io()) // Subscribes on a background thread, which is Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //  Displays the result on the main thread (UI thread)
                .map { it.results } // Takes the list of vehicles in VehiclesResult pass it on to the next operator
                .subscribeWith(createVehicleObserver()) // The glue that connects networkService.fetchVehicle() with createVehicleObserver()
        )
    }

    private fun createVehicleObserver(): DisposableSingleObserver<List<Vehicle>> {
        return object : DisposableSingleObserver<List<Vehicle>>() {

            override fun onSuccess(vehicles: List<Vehicle>) {
                inProgressMLD.value = true
                isErrorMLD.value = false
                vehicleListMLD.value = vehicles
                inProgressMLD.value = false
            }

            override fun onError(e: Throwable) {
                inProgressMLD.value = true
                isErrorMLD.value = true
                Log.e("onError()", "Error: ${e.message}")
                inProgressMLD.value = false
            }
        }
    }
}
