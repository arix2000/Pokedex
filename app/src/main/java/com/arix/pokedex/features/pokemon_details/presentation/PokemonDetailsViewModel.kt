package com.arix.pokedex.features.pokemon_details.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonUseCase
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsEvent
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(val getPokemonUseCase: GetPokemonUseCase) : ViewModel() {

    private var _state by mutableStateOf(PokemonDetailsState()) // use without 'by'
    val state: PokemonDetailsState = _state

    private var getPokemonDetailsJob: Job? = null

    fun invokeEvent(event: PokemonDetailsEvent) {
        when(event) {
            is PokemonDetailsEvent.GetInitialData -> {
                getPokemonDetails(event.pokemonName)
            }
        }
    }

    private fun getPokemonDetails(name: String) {
        getPokemonDetailsJob?.cancel()
        getPokemonDetailsJob = viewModelScope.launch {
            _state = _state.copy(isLoading = true)
            getPokemonUseCase(name).run {
                _state = when (this) {
                    is Resource.Success -> _state.copy(pokemonDetails = data, isLoading = false)
                    is Resource.Error -> _state.copy(errorMessage = message, isLoading = false)
                }
            }
        }
    }
}