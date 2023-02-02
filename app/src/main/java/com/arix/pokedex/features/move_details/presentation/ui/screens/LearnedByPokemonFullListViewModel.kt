package com.arix.pokedex.features.move_details.presentation.ui.screens

import androidx.lifecycle.ViewModel
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonListByNamesUseCase
import com.arix.pokedex.utils.ApiResponse

class LearnedByPokemonFullListViewModel(val getPokemonListByNames: GetPokemonListByNamesUseCase) :
    ViewModel() {

    suspend fun getPokemonListFrom(names: List<String>): List<ApiResponse<PokemonDetails>> {
        return getPokemonListByNames(names)
    }
}