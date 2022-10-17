package com.arix.pokedex.core.di

import com.arix.pokedex.features.moves.data.MovesRemoteDataSource
import com.arix.pokedex.features.poke_list.data.PokemonRemoteDataSource
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { PokemonRemoteDataSource(get()) }

    factory { PokemonDetailsRemoteDataSource(get()) }

    factory { MovesRemoteDataSource(get()) }
}