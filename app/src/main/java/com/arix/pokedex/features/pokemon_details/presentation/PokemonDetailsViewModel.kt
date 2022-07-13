package com.arix.pokedex.features.pokemon_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonUseCase
import com.arix.pokedex.features.pokemon_details.domain.model.EvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.model.RawEvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonEvolutionChainUseCase
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonSpeciesUseCase
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsEvent
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsState
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonEvolutionState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.*

class PokemonDetailsViewModel(
    val getPokemonUseCase: GetPokemonUseCase,
    val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase,
    val getPokemonEvolutionChainUseCase: GetPokemonEvolutionChainUseCase
) : ViewModel() {

    private var _state = mutableStateOf(PokemonDetailsState())
    val state: State<PokemonDetailsState> = _state

    private var _evolutionSectionState = mutableStateOf(PokemonEvolutionState())
    val evolutionSectionState: State<PokemonEvolutionState> = _evolutionSectionState

    private var getPokemonInitialData: Job? = null
    private var getPokemonDetailsList: Job? = null

    fun invokeEvent(event: PokemonDetailsEvent) {
        when (event) {
            is PokemonDetailsEvent.GetInitialData ->
                getInitialData(event.pokemonName)

            is PokemonDetailsEvent.GetEvolutionPokemonDetailsList ->
                getEvolutionPokemonDetailsList(event.pokemonNames)
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
                    is Resource.Success -> {
                        _state.value = _state.value.copy(species = data)
                        getPokemonEvolutionChain(data?.evolution_chain?.url!!)
                    }
                    is Resource.Error -> onError(message)
                }
            }
        }
    }

    private fun getPokemonEvolutionChain(evolutionChainUrl: String) {
        viewModelScope.launch {
            with(getPokemonEvolutionChainUseCase(getEvolutionChainIdFrom(evolutionChainUrl))) {
                when (this) {
                    is Resource.Success -> _state.value =
                        _state.value.copy(evolutionChain = data, isLoading = false)
                    is Resource.Error -> onError(message)
                }
            }
        }
    }

    private fun getEvolutionChainIdFrom(url: String): Int =
        url.dropLast(1).takeLastWhile { it.isDigit() }.toInt()

    private fun getEvolutionPokemonDetailsList(pokemonRawSteps: List<RawEvolutionStep>) {
        getPokemonDetailsList?.cancel()
        getPokemonDetailsList = viewModelScope.launch {
            _evolutionSectionState.value = _evolutionSectionState.value.copy(isLoading = true)
            val pokemonDetailsResponses = pokemonRawSteps.map { evolutionStepRaw ->
                evolutionStepRaw.pokemonNames.map { async(Dispatchers.IO) { getPokemon(it) } }
            }

            _evolutionSectionState.value = _evolutionSectionState.value.copy(
                pokemonEvolutionSteps = pokemonDetailsResponses.mapIndexed { index, list ->
                    EvolutionStep(list.awaitAll(), pokemonRawSteps[index].pokemonEvolutionDetail)
                },
                isLoading = false
            )
        }
    }

    private suspend fun getPokemon(name: String): PokemonDetails {
        getPokemonUseCase(name).run {
            return when (this) {
                is Resource.Success -> data!!
                is Resource.Error -> PokemonDetails.EMPTY
            }
        }
    }

    private fun onError(message: String?) {
        _state.value = _state.value.copy(errorMessage = message, isLoading = false)
    }
}