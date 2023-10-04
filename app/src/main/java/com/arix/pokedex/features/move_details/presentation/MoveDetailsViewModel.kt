package com.arix.pokedex.features.move_details.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.core.Constants.MoveScreen.LEARNED_BY_POKEMON_LIST_MAX_SIZE
import com.arix.pokedex.extensions.ifAllErrors
import com.arix.pokedex.features.move_details.domain.GetMoveUseCase
import com.arix.pokedex.features.move_details.presentation.ui.MoveDetailsEvent
import com.arix.pokedex.features.move_details.presentation.ui.MoveDetailsState
import com.arix.pokedex.features.move_details.presentation.ui.components.learnedByPokemon.LearnedByPokemonState
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonBasicData
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonListByNamesUseCase
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoveDetailsViewModel(
    private val getMoveUseCase: GetMoveUseCase,
    private val getPokemonListByNamesUseCase: GetPokemonListByNamesUseCase
) : ViewModel() {

    var state = mutableStateOf(MoveDetailsState())
        private set

    var learnedByPokemonState = mutableStateOf(LearnedByPokemonState())
        private set

    private var getMoveJob: Job? = null
    private var getPokemonDetailsJob: Job? = null

    fun invokeEvent(event: MoveDetailsEvent) {
        when (event) {
            is MoveDetailsEvent.LoadMoveDetailsEvent -> loadMoveDetails(event.moveId)
            is MoveDetailsEvent.LoadLearnedBySection -> loadLearnedByPokemonList(event.pokemonList)
        }
    }

    private fun loadMoveDetails(moveId: Int) {
        getMoveJob = viewModelScope.launch {
            state.run {
                value = value.copy(isLoading = true)
                val response = getMoveUseCase(moveId.toString())
                value = when (response) {
                    is ApiResponse.Success -> {
                        val move = response.data!!
                        loadLearnedByPokemonList(move.learnedByPokemon)
                        value.copy(
                            move = move,
                            isLoading = false
                        )
                    }
                    is ApiResponse.Error -> value.copy(
                        errorMessage = response.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun loadLearnedByPokemonList(pokemonList: List<PokemonBasicData>) {
        getPokemonDetailsJob = viewModelScope.launch {
            val pokemonNames = pokemonList.take(LEARNED_BY_POKEMON_LIST_MAX_SIZE).map { it.name }
            learnedByPokemonState.run {
                value = value.copy(isLoading = true)
                val response = getPokemonListByNamesUseCase(pokemonNames)
                value = when {
                    response.ifAllErrors() -> value.copy(
                        errorMessage = response.firstOrNull()?.message,
                        isLoading = false
                    )
                    else -> value.copy(pokemonList = response.map { it.data!! }, isLoading = false)
                }
            }
        }
    }
}