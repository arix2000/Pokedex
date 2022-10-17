package com.arix.pokedex.core.di

import android.content.Context
import com.arix.pokedex.extensions.dataStore
import com.arix.pokedex.features.moves.data.data_sources.MovesLocalDataSource
import com.arix.pokedex.features.moves.data.data_sources.MovesRemoteDataSource
import com.arix.pokedex.features.poke_list.data.data_sources.PokemonLocalDataSource
import com.arix.pokedex.features.poke_list.data.data_sources.PokemonRemoteDataSource
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { PokemonRemoteDataSource(get()) }

    factory { PokemonLocalDataSource(get()) }

    factory { PokemonDetailsRemoteDataSource(get()) }

    factory { MovesRemoteDataSource(get()) }

    factory { MovesLocalDataSource(get()) }
}