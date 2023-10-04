package com.arix.pokedex.core.di

import android.content.Context
import com.arix.pokedex.extensions.dataStore
import com.arix.pokedex.features.common.search_view.SearchableLazyColumnViewModel
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.model.item_details.ItemDetails
import com.arix.pokedex.features.items.presentation.ItemsViewModel
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.move_details.presentation.MoveDetailsViewModel
import com.arix.pokedex.features.move_details.presentation.ui.screens.LearnedByPokemonFullListViewModel
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.presentation.MovesViewModel
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.features.pokemon_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.splash_activity.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get()) }

    viewModel { PokemonDetailsViewModel(get(), get(), get()) }

    viewModel { MovesViewModel(get()) }

    viewModel { MoveDetailsViewModel(get(), get()) }

    viewModel { SplashViewModel(get<Context>().dataStore) }

    viewModel { LearnedByPokemonFullListViewModel(get()) }

    viewModel { ItemsViewModel(get(), get()) }

    viewModel(named(PokemonItem::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<PokemonItem>(
            getItemList = params.get()
        )
    }

    viewModel(named(MoveItem::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<MoveItem>(
            getItemList = params.get()
        )
    }

    viewModel(named(Item::class.java.simpleName)) { params ->
        SearchableLazyColumnViewModel<Item>(
            getItemList = params.get()
        )
    }
}