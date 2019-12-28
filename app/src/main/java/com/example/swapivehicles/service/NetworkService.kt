package com.example.swapivehicles.service

import com.example.swapivehicles.api_endpoint.StarWarsApi
import javax.inject.Inject

class NetworkService {

    @Inject
    lateinit var starWarsApi: StarWarsApi

    companion object {
        val BASE_URL = "https://swapi.co/"
    }
}