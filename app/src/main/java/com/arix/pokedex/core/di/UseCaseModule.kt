package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.data.repository.PokemonRepositoryImpl
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokemonListUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonUseCase(get<PokemonRepositoryImpl>()) }
}