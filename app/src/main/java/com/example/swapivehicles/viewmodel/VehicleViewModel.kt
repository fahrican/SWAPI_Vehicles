package com.example.swapivehicles.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swapivehicles.adapter.VehicleAdapter
import com.example.swapivehicles.di.DaggerApiComponent
import com.example.swapivehicles.model.Vehicle
import com.example.swapivehicles.service.NetworkService
import io.reactivex.disposables.CompositeDisposable
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
    lateinit var isErrorLD: LiveData<Boolean>
}
