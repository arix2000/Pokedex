package com.arix.pokedex.features.pokemon_details.presentation

import androidx.compose.runtime.State
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

    private var _state = mutableStateOf(PokemonDetailsState())
    val state: State<PokemonDetailsState> = _state

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
            _state.value = _state.value.copy(isLoading = true)
            getPokemonUseCase(name).run {
                _state.value = when (this) {
                    is Resource.Success -> _state.value.copy(pokemonDetails = data, isLoading = false)
                    is Resource.Error -> _state.value.copy(errorMessage = message, isLoading = false)
                }
            }
        }
    }
}