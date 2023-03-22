package com.arix.pokedex.core.di

import android.content.Context
import com.arix.pokedex.extensions.dataStore
import com.arix.pokedex.features.common.search_view.SearchableLazyColumnViewModel
import com.arix.pokedex.features.items.domain.model.move_details.ItemDetails
import com.arix.pokedex.features.items.presentation.ItemsViewModel
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.move_details.presentation.MoveDetailsViewModel
import com.arix.pokedex.features.move_details.presentation.ui.screens.LearnedByPokemonFullListViewModel
import com.arix.pokedex.features.moves.presentation.MovesViewModel
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.splash_activity.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get(), get()) }

    viewModel { PokemonDetailsViewModel(get(), get(), get()) }

    viewModel { MovesViewModel(get(), get()) }

    viewModel { MoveDetailsViewModel(get(), get()) }

    viewModel { SplashViewModel(get(), get(), get(), get(), get(), get(), get<Context>().dataStore) }

    viewModel { LearnedByPokemonFullListViewModel(get()) }

    viewModel { ItemsViewModel(get(), get()) }

    viewModel(named(PokemonDetails::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<PokemonDetails>(
            itemsLimit = params.get(),
            itemNames = params.get(),
            emptyItem = params.get(),
            objectFromNames = params.get()
        )
    }

    viewModel(named(UiMove::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<UiMove>(
            itemsLimit = params.get(),
            itemNames = params.get(),
            emptyItem = params.get(),
            objectFromNames = params.get()
        )
    }

    viewModel(named(ItemDetails::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<ItemDetails>(
            itemsLimit = params.get(),
            itemNames = params.get(),
            emptyItem = params.get(),
            objectFromNames = params.get()
        )
    }
}