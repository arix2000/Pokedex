package com.arix.pokedex.core.di

import android.content.Context
import com.arix.pokedex.extensions.dataStore
import com.arix.pokedex.features.common.search_view.SearchableLazyColumnViewModel
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.presentation.MovesViewModel
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.splash_activity.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get(), get()) }

    viewModel { PokemonDetailsViewModel(get(), get(), get()) }

    viewModel { MovesViewModel(get(), get()) }

    viewModel { SplashViewModel(get(), get(), get(), get(), get<Context>().dataStore) }

    viewModel(named(PokemonDetails::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<PokemonDetails>(
            itemsLimit = params.get(),
            itemNames = params.get(),
            emptyItem = params.get(),
            objectFromNames = params.get()
        )
    }

    viewModel(named(Move::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<Move>(
            itemsLimit = params.get(),
            itemNames = params.get(),
            emptyItem = params.get(),
            objectFromNames = params.get()
        )
    }
}