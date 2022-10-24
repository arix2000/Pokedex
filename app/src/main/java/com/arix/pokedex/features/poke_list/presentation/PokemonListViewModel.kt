package com.arix.pokedex.features.poke_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonListByNamesUseCase
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonNamesUseCase
import com.arix.pokedex.features.poke_list.presentation.ui.PokemonListState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonListViewModel(
    val getPokemonListByNames: GetPokemonListByNamesUseCase,
    val getPokemonNamesUseCase: GetPokemonNamesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    private var getPokemonNamesJob: Job? = null

    init {
        getPokemonNames()
    }

    private fun getPokemonNames() {
        getPokemonNamesJob = viewModelScope.launch {
            getPokemonNamesUseCase().collect { _state.value = _state.value.copy(pokemonNames = it) }
        }
    }

    suspend fun getPokemonListFrom(names: List<String>): List<Resource<PokemonDetails>> {
        return getPokemonListByNames(names)
    }
}