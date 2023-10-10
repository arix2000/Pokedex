package com.arix.pokedex.features.move_details.presentation.ui.screens

import androidx.lifecycle.ViewModel
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.utils.ApiResponse

class LearnedByPokemonFullListViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) :
    ViewModel() {

    suspend fun getPokemonList(
        offset: Int,
        searchQuery: String,
        limitedList: List<String>
    ): ApiResponse<Page<PokemonItem>> {
        return getPokemonListUseCase(offset, searchQuery, limitedList = limitedList)
    }
}