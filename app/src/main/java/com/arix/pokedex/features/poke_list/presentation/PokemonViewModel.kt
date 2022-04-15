package com.arix.pokedex.features.poke_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.core.Constants.PokemonListScreen.POKEMON_LIST_INITIAL_OFFSET
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonBasicData
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonUseCase
import com.arix.pokedex.features.poke_list.presentation.pokemon_list.PokemonListState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.*

class PokemonViewModel(
    val getPokemonListUseCase: GetPokemonListUseCase,
    val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    private var getNextOrInitialPokemonListJob: Job? = null

    init {
        getNextOrInitialPokemonList()
    }

    fun getNextOrInitialPokemonList() {
        getNextOrInitialPokemonListJob?.cancel()
        setProperlyLoadingBasedOnPokemonList()
        getNextOrInitialPokemonListJob = viewModelScope.launch {
            getPokemonListUseCase(
                _state.value.pokemonList?.size ?: POKEMON_LIST_INITIAL_OFFSET
            ).run {
                when (this) {
                    is Resource.Success -> getPokemonDetailsList(data?.pokemonList!!)
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isInitialLoading = false,
                            isLoadingNext = false,
                            errorMessage = message
                        )
                    }
                }
            }
        }
    }

    private fun setProperlyLoadingBasedOnPokemonList() {
        if (_state.value.pokemonList == null)
            _state.value = _state.value.copy(
                isInitialLoading = true,
                isLoadingNext = false,
                errorMessage = null
            )
        else
            _state.value = _state.value.copy(
                isInitialLoading = false,
                isLoadingNext = true,
                errorMessage = null
            )
    }

    private fun getPokemonDetailsList(pokemonBasicDataList: List<PokemonBasicData>) {
        viewModelScope.launch {
            val pokemonDetailsResponses = pokemonBasicDataList.map {
                async(Dispatchers.IO) { getPokemon(it.name) }
            }

            _state.value = _state.value.copy(
                pokemonList = _state.value.pokemonList?.apply { addAll(pokemonDetailsResponses.awaitAll()) }
                    ?: pokemonDetailsResponses.awaitAll().toMutableList(),
                isInitialLoading = false,
                isLoadingNext = false
            )
        }
    }


    private suspend fun getPokemon(name: String): PokemonDetails {
        getPokemonUseCase(name).run {
            when (this) {
                is Resource.Success -> return data!!
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isInitialLoading = false,
                        errorMessage = message
                    )
                }
            }
        }
        return PokemonDetails.EMPTY
    }

}