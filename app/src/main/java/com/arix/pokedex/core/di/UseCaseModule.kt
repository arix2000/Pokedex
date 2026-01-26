package com.arix.pokedex.core.di

import com.arix.pokedex.features.items.data.ItemRepositoryImpl
import com.arix.pokedex.features.items.domain.use_cases.GetItemDetailsUseCase
import com.arix.pokedex.features.items.domain.use_cases.GetItemListUseCase
import com.arix.pokedex.features.items.domain.use_cases.GetItemsByNamesUseCase
import com.arix.pokedex.features.move_details.domain.GetMoveUseCase
import com.arix.pokedex.features.moves.data.MovesRepositoryImpl
import com.arix.pokedex.features.pokemon_list.data.PokemonRepositoryImpl
import com.arix.pokedex.features.pokemon_list.domain.use_cases.*
import com.arix.pokedex.features.pokemon_details.data.PokemonDetailsRepositoryImpl
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonEvolutionChainUseCase
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonSpeciesUseCase
import com.arix.pokedex.features.moves.domain.use_cases.GetMoveListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokemonListUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonListByNamesUseCase(get<PokemonRepositoryImpl>()) }

    factory { GetPokemonSpeciesUseCase(get<PokemonDetailsRepositoryImpl>()) }

    factory { GetPokemonEvolutionChainUseCase(get<PokemonDetailsRepositoryImpl>()) }

    factory { GetMoveListUseCase(get<MovesRepositoryImpl>()) }

    factory { GetMoveUseCase(get<MovesRepositoryImpl>()) }

    factory { GetItemListUseCase(get<ItemRepositoryImpl>()) }

    factory { GetItemsByNamesUseCase(get<ItemRepositoryImpl>()) }

    factory { GetItemDetailsUseCase(get<ItemRepositoryImpl>()) }
}