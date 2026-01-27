package com.arix.pokedex.core.di

import com.arix.pokedex.features.items.data.data_sources.ItemRemoteDataSource
import com.arix.pokedex.features.locations.data.LocationRemoteDataSource
import com.arix.pokedex.features.moves.data.data_sources.MovesRemoteDataSource
import com.arix.pokedex.features.pokemon_list.data.data_sources.PokemonRemoteDataSource
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { PokemonRemoteDataSource(get(), get()) }

    factory { PokemonDetailsRemoteDataSource(get()) }

    factory { MovesRemoteDataSource(get(), get()) }

    factory { ItemRemoteDataSource(get(), get()) }

    factory { LocationRemoteDataSource(get(), get()) }
}