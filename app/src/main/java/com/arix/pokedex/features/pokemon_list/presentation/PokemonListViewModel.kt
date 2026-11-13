package com.arix.pokedex.features.pokemon_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.features.pokemon_list.presentation.ui.PokemonListState
import com.arix.pokedex.utils.ApiResponse

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    suspend fun getPokemonList(offset: Int, searchQuery: String): ApiResponse<Page<PokemonItem>> {
        return getPokemonListUseCase(offset = offset, searchQuery)
    }
}