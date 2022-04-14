package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.data.remote_data_source.PokemonRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { PokemonRemoteDataSource(get()) }
}