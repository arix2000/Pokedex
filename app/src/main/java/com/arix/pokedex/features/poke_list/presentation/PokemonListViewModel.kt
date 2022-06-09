package com.arix.pokedex.features.poke_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.core.Constants.PokemonListScreen.POKEMON_LIST_INITIAL_OFFSET
import com.arix.pokedex.core.Constants.PokemonListScreen.POKEMON_SEARCH_LIST_ITEM_LIMIT
import com.arix.pokedex.extensions.isSizeEqualsOrGreaterThan
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonUseCase
import com.arix.pokedex.features.poke_list.presentation.ui.PokemonListEvent
import com.arix.pokedex.features.poke_list.presentation.ui.PokemonListState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.*
import java.io.InputStream

class PokemonListViewModel(
    val getPokemonListUseCase: GetPokemonListUseCase,
    val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    private lateinit var pokemonNames: List<String>
    var actualSearchQuery = ""
        set(value) {
            field = value
            searchPokemon()
        }
    private var previousSearchQuery: String = ""

    private var getNextOrInitialPokemonListJob: Job? = null
    private var getPokemonDetailsListJob: Job? = null

    init {
        getNextOrInitialPokemonList()
    }

    fun cachePokemonNames(pokemonNamesInputStream: InputStream) {
        pokemonNames = pokemonNamesInputStream.reader().readLines()
    }

    fun invokeEvent(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.GetNextPage,
            is PokemonListEvent.OnRetryClicked ->
                getNextPage()
            is PokemonListEvent.SearchByQuery ->
                actualSearchQuery = event.query.toLowerCase(LocaleList.current)
        }
    }

    private fun getNextPage() {
        if (state.value.isSearching)
            getNextOrInitialSearchedList()
        else
            getNextOrInitialPokemonList()
    }

    private fun getNextOrInitialPokemonList() {
        getNextOrInitialPokemonListJob?.cancel()
        getPokemonDetailsListJob?.cancel()
        setProperlyLoadingBasedOnPokemonList()
        getNextOrInitialPokemonListJob = viewModelScope.launch {
            getPokemonListUseCase(
                _state.value.pokemonList?.size ?: POKEMON_LIST_INITIAL_OFFSET
            ).run {
                when (this) {
                    is Resource.Success ->
                        getPokemonDetailsList(data?.pokemonList?.map { it.name }!!) {
                            _state.value = _state.value.copy(isListEndedReached = data.next == null)
                        }
                    is Resource.Error ->
                        _state.value = _state.value.copy(
                            isInitialLoading = false,
                            isLoadingNext = false,
                            errorMessage = message
                        )
                }
            }
        }
    }

    private fun setProperlyLoadingBasedOnPokemonList() {
        if (_state.value.pokemonList == null && !_state.value.isSearching)
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

    private fun searchPokemon() {
        val previousQuery = previousSearchQuery
        previousSearchQuery = actualSearchQuery
        if (actualSearchQuery == previousQuery) return
        if (actualSearchQuery.isBlank() && previousQuery.isNotBlank()) {
            _state.value = _state.value.copy(
                pokemonList = null,
                isListEndedReached = false,
                isSearchResultsEmpty = false
            )
            getNextOrInitialPokemonList()
            _state.value = _state.value.copy(isSearching = false)
            return
        }
        _state.value = _state.value.copy(pokemonList = null)
        _state.value = _state.value.copy(
            isSearching = true,
            isInitialLoading = false,
            isLoadingNext = true,
            isSearchResultsEmpty = false,
            isListEndedReached = false,
            errorMessage = null
        )
        getNextOrInitialSearchedList()
    }

    private fun getNextOrInitialSearchedList() {
        val filteredPokemonNames = getFilteredAndSortedPokemonList()

        _state.value = _state.value.copy(
            isSearchResultsEmpty = filteredPokemonNames.isEmpty(),
            isLoadingNext = filteredPokemonNames.isNotEmpty()
        )

        getPokemonDetailsList(
            filteredPokemonNames.subList(
                (_state.value.pokemonList?.size ?: 0), getDefaultLimitOrHowManyLeft(
                    filteredPokemonNames
                )
            )
        ) {
            _state.value =
                _state.value.copy(
                    isListEndedReached = _state.value.pokemonList
                        .isSizeEqualsOrGreaterThan(filteredPokemonNames)
                )
        }
    }

    private fun getFilteredAndSortedPokemonList(): List<String> {
        val filteredList = pokemonNames.filter { it.contains(actualSearchQuery) }
        val filteredAndSorted = filteredList
            .filter { it.startsWith(actualSearchQuery) }
            .toMutableList()
        filteredList.forEach {
            if (!filteredAndSorted.contains(it)) filteredAndSorted.add(it)
        }
        return filteredAndSorted
    }

    private fun getDefaultLimitOrHowManyLeft(filteredPokemonNames: List<String>): Int {
        val actualList = _state.value.pokemonList ?: emptyList()

        return when {
            isSizeEqualOrLowerThanLimit(filteredPokemonNames.size) ->
                filteredPokemonNames.size
            canTakeNextItems(actualList.size, filteredPokemonNames.size) ->
                POKEMON_SEARCH_LIST_ITEM_LIMIT + actualList.size
            else ->
                filteredPokemonNames.size
        }
    }

    private fun isSizeEqualOrLowerThanLimit(size: Int) = POKEMON_SEARCH_LIST_ITEM_LIMIT >= size

    private fun canTakeNextItems(actualListSize: Int, allItemsListSize: Int) =
        actualListSize < allItemsListSize - POKEMON_SEARCH_LIST_ITEM_LIMIT

    private fun getPokemonDetailsList(pokemonNames: List<String>, onJobCompleted: () -> Unit) {
        getPokemonDetailsListJob?.cancel()
        getNextOrInitialPokemonListJob?.cancel()
        getPokemonDetailsListJob = viewModelScope.launch {
            val pokemonDetailsResponses = pokemonNames.map {
                async(Dispatchers.IO) { getPokemon(it) }
            }

            _state.value = _state.value.copy(
                pokemonList = (_state.value.pokemonList
                    ?: emptyList()).plus(pokemonDetailsResponses.awaitAll()),
                isInitialLoading = false,
                isLoadingNext = false
            )
        }
        getPokemonDetailsListJob?.invokeOnCompletion { onJobCompleted() }
    }

    private suspend fun getPokemon(name: String): PokemonDetails {
        getPokemonUseCase(name).run {
            when (this) {
                is Resource.Success -> return data!!
                is Resource.Error -> return PokemonDetails.EMPTY
            }
        }
    }
}