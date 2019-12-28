package com.example.swapivehicles.di

import com.example.swapivehicles.service.NetworkService
import com.example.swapivehicles.view.MainActivity
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(networkService: NetworkService)
}