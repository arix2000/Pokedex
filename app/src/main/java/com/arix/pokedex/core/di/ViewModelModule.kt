package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get(), get()) }

    viewModel { PokemonDetailsViewModel(get(), get(), get()) }
}