package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.data.repository.PokemonRepositoryImpl
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
   factory { PokemonRepositoryImpl(get()) }

   factory { PokemonDetailsRepositoryImpl(get()) }
}