package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.data.repository.PokemonRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
   factory { PokemonRepositoryImpl(get()) }
}