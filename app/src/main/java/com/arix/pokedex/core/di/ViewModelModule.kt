package com.arix.pokedex.core.di

import com.arix.pokedex.features.poke_list.presentation.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonViewModel(get(), get()) }
}