package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.data.repository.PokemonRepositoryImpl
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonUseCase
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRepositoryImpl
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonEvolutionChainUseCase
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonSpeciesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokemonListUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonSpeciesUseCase(get<PokemonDetailsRepositoryImpl>()) }

    factory { GetPokemonEvolutionChainUseCase(get<PokemonDetailsRepositoryImpl>()) }
}