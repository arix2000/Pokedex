package com.arix.pokedex.core.di

import com.arix.pokedex.core.network.NetworkManager
import org.koin.dsl.module

val networkManager = NetworkManager()

val networkModule = module {
    single { networkManager.provideRetrofit() }
    factory { networkManager.provideApi(get()) }
}