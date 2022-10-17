package com.arix.pokedex.core.di

import com.arix.pokedex.features.moves.data.MovesRepositoryImpl
import com.arix.pokedex.features.poke_list.data.PokemonRepositoryImpl
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
   factory { PokemonRepositoryImpl(get()) }

   factory { PokemonDetailsRepositoryImpl(get()) }

   factory { MovesRepositoryImpl(get()) }
}