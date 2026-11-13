package com.arix.pokedex.features.limited_list.presentation

import androidx.lifecycle.ViewModel
import com.arix.pokedex.core.Constants.MoveScreen.ROW_ITEM_LIMIT
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.domain.usecases.GetLocationsUseCase
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.domain.use_cases.GetMoveListUseCase
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.utils.ApiResponse

class LearnedByPokemonFullListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getMovesUseCase: GetMoveListUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
) :
    ViewModel() {

    suspend fun getPokemonList(
        offset: Int,
        searchQuery: String,
        limitedList: List<String>
    ): ApiResponse<Page<PokemonItem>> {
        return getPokemonListUseCase(offset, searchQuery, limitedList = limitedList)
    }

    suspend fun getMoves(
        offset: Int,
        searchQuery: String,
        limitedList: List<String>
    ): ApiResponse<Page<MoveItem>> {
        return getMovesUseCase(offset, ROW_ITEM_LIMIT, searchQuery, limitedList = limitedList)
    }

    suspend fun getLocations(
        offset: Int,
        searchQuery: String,
        limitedList: List<String>
    ): ApiResponse<Page<LocationItem>> {
        return getLocationsUseCase(offset, ROW_ITEM_LIMIT, searchQuery, limitedList = limitedList)
    }
}