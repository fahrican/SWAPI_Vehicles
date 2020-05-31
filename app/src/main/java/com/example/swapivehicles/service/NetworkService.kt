package com.example.swapivehicles.service

import com.example.swapivehicles.api_endpoint.StarWarsApi
import com.example.swapivehicles.di.DaggerApiComponent
import com.example.swapivehicles.model.VehiclesResult
import io.reactivex.Single
import javax.inject.Inject

class NetworkService {

    @Inject
    lateinit var starWarsApi: StarWarsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchVehicle(): Single<VehiclesResult> {
        return starWarsApi.getVehicles()
    }

    companion object {
        val BASE_URL = "https://swapi.dev/"
    }
}