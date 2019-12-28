package com.example.swapivehicles.di

import com.example.swapivehicles.adapter.VehicleAdapter
import com.example.swapivehicles.api_endpoint.StarWarsApi
import com.example.swapivehicles.model.Vehicle
import com.example.swapivehicles.service.NetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

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

    @Provides
    fun provideStarWarsApi(): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }
}