package com.arix.pokedex.features.pokemon_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonUseCase
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonSpeciesUseCase
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsEvent
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    val getPokemonUseCase: GetPokemonUseCase,
    val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase
) : ViewModel() {

    private var _state = mutableStateOf(PokemonDetailsState())
    val state: State<PokemonDetailsState> = _state

    private var getPokemonInitialData: Job? = null

    fun invokeEvent(event: PokemonDetailsEvent) {
        when (event) {
            is PokemonDetailsEvent.GetInitialData -> {
                getInitialData(event.pokemonName)
            }
        }
    }

    private fun getInitialData(pokemonName: String) {
        getPokemonInitialData?.cancel()
        getPokemonInitialData = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            with(getPokemonUseCase(pokemonName)) {
                when (this) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(pokemonDetails = data)
                        getPokemonSpecies(data?.species?.name ?: "")
                    }
                    is Resource.Error -> onError(message)
                }
            }
        }
    }

    private fun getPokemonSpecies(pokemonSpeciesName: String) {
        viewModelScope.launch {
            with(getPokemonSpeciesUseCase(pokemonSpeciesName)) {
                when (this) {
                    is Resource.Success -> _state.value =
                        _state.value.copy(species = data, isLoading = false)
                    is Resource.Error -> onError(message)
                }
            }
        }
    }

    private fun onError(message: String?) {
        _state.value = _state.value.copy(errorMessage = message, isLoading = false)
    }
}