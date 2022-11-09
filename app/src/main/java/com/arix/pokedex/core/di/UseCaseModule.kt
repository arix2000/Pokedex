package com.arix.pokedex.core.di

import com.arix.pokedex.features.moves.data.MovesRepositoryImpl
import com.arix.pokedex.features.moves.domain.use_cases.GetMoveNamesUseCase
import com.arix.pokedex.features.moves.domain.use_cases.GetMovesByNamesUseCase
import com.arix.pokedex.features.moves.domain.use_cases.SaveMoveNamesUseCase
import com.arix.pokedex.features.poke_list.data.PokemonRepositoryImpl
import com.arix.pokedex.features.poke_list.domain.use_cases.*
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRepositoryImpl
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonEvolutionChainUseCase
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonSpeciesUseCase
import com.arix.pokedex.features.splash_activity.GetRawMoveList
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokemonListUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonUseCase(get<PokemonRepositoryImpl>()) }

    factory { SavePokemonNamesUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonNamesUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonListByNamesUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonSpeciesUseCase(get<PokemonDetailsRepositoryImpl>()) }

    factory { GetPokemonEvolutionChainUseCase(get<PokemonDetailsRepositoryImpl>()) }

    factory { GetRawMoveList(get<MovesRepositoryImpl>()) }

    factory { GetMoveNamesUseCase(get<MovesRepositoryImpl>()) }

    factory { SaveMoveNamesUseCase(get<MovesRepositoryImpl>()) }

    factory { GetMovesByNamesUseCase(get<MovesRepositoryImpl>()) }
}