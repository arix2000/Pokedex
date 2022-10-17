package com.arix.pokedex.core.di

import android.content.Context
import com.arix.pokedex.extensions.dataStore
import com.arix.pokedex.features.moves.presentation.MovesViewModel
import com.arix.pokedex.features.poke_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.splash_activity.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get(), get()) }

    viewModel { PokemonDetailsViewModel(get(), get(), get()) }

    viewModel { MovesViewModel(get()) }

    viewModel { SplashViewModel(get(), get(), get(), get(), get<Context>().dataStore) }
}