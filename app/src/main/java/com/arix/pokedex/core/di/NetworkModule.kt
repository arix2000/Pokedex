package com.arix.pokedex.core.di

import com.arix.pokedex.core.network.NetworkManager
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkManager = NetworkManager()

val networkModule = module {
    single(named("PokeApi")) { networkManager.providePokeApiRetrofit() }

    factory { networkManager.providePokeApi(get(named("PokeApi"))) }

    single(named("PokeLists")) { networkManager.providePokeListsApiRetrofit() }

    factory { networkManager.providePokeListsApi(get(named("PokeLists"))) }
}