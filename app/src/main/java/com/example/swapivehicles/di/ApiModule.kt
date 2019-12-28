package com.example.swapivehicles.di

import com.example.swapivehicles.adapter.VehicleAdapter
import com.example.swapivehicles.model.Vehicle
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    @Provides
    fun provideVehicleList(): ArrayList<Vehicle> {
        return ArrayList()
    }

    @Provides
    fun provideVehicleAdapter(vehicles: ArrayList<Vehicle>): VehicleAdapter {
        return VehicleAdapter(vehicles)
    }
}