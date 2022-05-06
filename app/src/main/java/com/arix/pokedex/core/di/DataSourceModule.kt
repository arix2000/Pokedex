package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.data.remote_data_source.PokemonRemoteDataSource
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { PokemonRemoteDataSource(get()) }

    factory { PokemonDetailsRemoteDataSource(get()) }
}