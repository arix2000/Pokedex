package com.arix.pokedex.features.pokemon_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonUseCase
import com.arix.pokedex.features.pokemon_details.domain.model.EvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.model.PokemonEvolutionDetails
import com.arix.pokedex.features.pokemon_details.domain.model.RawEvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.model.species.Variety
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonEvolutionChainUseCase
import com.arix.pokedex.features.pokemon_details.domain.use_cases.GetPokemonSpeciesUseCase
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsEvent
import com.arix.pokedex.features.pokemon_details.presentation.ui.states.PokemonDetailsState
import com.arix.pokedex.features.pokemon_details.presentation.ui.states.PokemonEvolutionState
import com.arix.pokedex.features.pokemon_details.presentation.ui.states.PokemonVarietiesState
import com.arix.pokedex.utils.ApiResponse
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

    private var _varietiesSectionState = mutableStateOf(PokemonVarietiesState())
    val varietiesSectionState: State<PokemonVarietiesState> = _varietiesSectionState

    private var getPokemonInitialData: Job? = null
    private var getPokemonDetailsList: Job? = null
    private var getPokemonVarietiesList: Job? = null

    fun invokeEvent(event: PokemonDetailsEvent) {
        when (event) {
            is PokemonDetailsEvent.GetInitialData ->
                getInitialData(event.pokemonName)

            is PokemonDetailsEvent.GetEvolutionPokemonDetailsList ->
                getEvolutionPokemonDetailsList(event.pokemonRawEvolutionSteps)

            is PokemonDetailsEvent.GetPokemonVarietiesDetailsList -> getPokemonVarietiesDetailsList(
                event.varieties
            )
        }
    }

    private fun getInitialData(pokemonName: String) {
        getPokemonInitialData?.cancel()
        getPokemonInitialData = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            with(getPokemonUseCase(pokemonName)) {
                when (this) {
                    is ApiResponse.Success -> {
                        _state.value = _state.value.copy(pokemonDetails = data)
                        getPokemonSpecies(data?.species?.name ?: "")
                    }
                    is ApiResponse.Error -> onError(message)
                }
            }
        }
    }

    private fun getPokemonSpecies(pokemonSpeciesName: String) {
        viewModelScope.launch {
            with(getPokemonSpeciesUseCase(pokemonSpeciesName)) {
                when (this) {
                    is ApiResponse.Success -> {
                        _state.value = _state.value.copy(species = data)
                        getPokemonEvolutionChain(data?.evolution_chain?.url!!)
                    }
                    is ApiResponse.Error -> onError(message)
                }
            }
        }
    }

    private fun getPokemonEvolutionChain(evolutionChainUrl: String) {
        viewModelScope.launch {
            with(getPokemonEvolutionChainUseCase(evolutionChainUrl.getIdFromUrl())) {
                when (this) {
                    is ApiResponse.Success -> _state.value =
                        _state.value.copy(evolutionChain = data, isLoading = false)
                    is ApiResponse.Error -> onError(message)
                }
            }
        }
    }

    private fun getEvolutionPokemonDetailsList(pokemonRawSteps: List<RawEvolutionStep>) {
        getPokemonDetailsList?.cancel()
        getPokemonDetailsList = viewModelScope.launch {
            _evolutionSectionState.value = _evolutionSectionState.value.copy(isLoading = true)
            val pokemonDetailsResponses = pokemonRawSteps.map { evolutionStepRaw ->
                evolutionStepRaw.pokemonToEvolutionDetailsMap.map {
                    async(Dispatchers.IO) {
                        getPokemon(it.key)
                    }
                }
            }

            _evolutionSectionState.value = _evolutionSectionState.value.copy(
                pokemonEvolutionSteps = pokemonDetailsResponses.mapIndexed { index, list ->
                    val pokemons: MutableList<PokemonEvolutionDetails> = mutableListOf()
                    list.awaitAll().forEach { pokemonDetails ->
                        val pokemonEvolutionDetails =
                            pokemonRawSteps[index].pokemonToEvolutionDetailsMap[pokemonDetails.id]
                                ?: listOf()
                        pokemons.add(
                            PokemonEvolutionDetails(
                                pokemonDetails,
                                pokemonEvolutionDetails
                            )
                        )
                    }
                    EvolutionStep(pokemons)
                },
                isLoading = false
            )
        }
    }

    private suspend fun getPokemon(id: Int): PokemonDetails {
        getPokemonUseCase(id.toString()).run {
            return when (this) {
                is ApiResponse.Success -> data!!
                is ApiResponse.Error -> PokemonDetails.EMPTY
            }
        }
    }

    private fun getPokemonVarietiesDetailsList(varieties: List<Variety>) {
        getPokemonVarietiesList?.cancel()
        getPokemonVarietiesList = viewModelScope.launch {
            _varietiesSectionState.value = _varietiesSectionState.value.copy(isLoading = true)
            val pokemonDetailsResponses = varieties.map {
                async(Dispatchers.IO) { getPokemon(it.pokemon.url.getIdFromUrl()) }
            }
            val varietiesDetails = pokemonDetailsResponses.awaitAll()
            _varietiesSectionState.value =
                _varietiesSectionState.value.copy(
                    pokemonVarietiesDetails = varietiesDetails,
                    isLoading = false,
                )
        }
    }

    private fun onError(message: String?) {
        _state.value = _state.value.copy(errorMessage = message, isLoading = false)
    }
}