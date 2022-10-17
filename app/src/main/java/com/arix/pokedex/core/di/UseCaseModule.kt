package com.arix.pokedex.core.di

import com.arix.pokedex.features.moves.data.MovesRepositoryImpl
import com.arix.pokedex.features.moves.domain.use_cases.GetMovesUseCase
import com.arix.pokedex.features.poke_list.data.PokemonRepositoryImpl
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

    factory { GetMovesUseCase(get<MovesRepositoryImpl>()) }
}