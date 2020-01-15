package com.example.swapivehicles.di

import com.example.swapivehicles.service.NetworkService
import com.example.swapivehicles.view.MainActivity
import com.example.swapivehicles.viewmodel.VehicleViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(networkService: NetworkService)

    fun inject(vehicleViewModel: VehicleViewModel)

    fun inject(mainActivity: MainActivity)
}