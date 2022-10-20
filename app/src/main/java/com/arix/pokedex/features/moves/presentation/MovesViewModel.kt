package com.arix.pokedex.features.moves.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.core.Constants.PokemonMovesScreen.MOVES_INITIAL_OFFSET
import com.arix.pokedex.core.Constants.PokemonMovesScreen.MOVES_ITEM_LIMIT
import com.arix.pokedex.extensions.filterAndSortListBy
import com.arix.pokedex.extensions.isSizeEqualsOrGreaterThan
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.use_cases.GetMoveNamesUseCase
import com.arix.pokedex.features.moves.domain.use_cases.GetMovesByNamesUseCase
import com.arix.pokedex.features.moves.domain.use_cases.GetMovesUseCase
import com.arix.pokedex.features.moves.presentation.ui.MovesScreenEvent
import com.arix.pokedex.features.moves.presentation.ui.MovesScreenState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovesViewModel(
    val getMovesUseCase: GetMovesUseCase,
    val getMoveNamesUseCase: GetMoveNamesUseCase,
    val getMovesByNamesUseCase: GetMovesByNamesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovesScreenState())
    val state: State<MovesScreenState> = _state

    private var moveNames: List<String> = emptyList()
    private var searchQuery = ""
        set(value) {
            field = value.toLowerCase(Locale.current).trim()
            searchMove()
        }
    private var previousSearchQuery = ""

    private var getMovesJob: Job? = null
    private var getMovesByNameJob: Job? = null
    private var getMoveNamesJob: Job? = null

    init {
        getMoveNames()
        getNextMoves()
    }

    fun invokeEvent(event: MovesScreenEvent) {
        when (event) {
            is MovesScreenEvent.GetNextMoves -> getNextMoves()
            is MovesScreenEvent.SearchByQuery -> searchQuery = event.query
            is MovesScreenEvent.GetNextPage -> if (state.value.searching) getNextSearch() else getNextMoves()
        }
    }

    private fun getMoveNames() {
        getMoveNamesJob = viewModelScope.launch {
            getMoveNamesUseCase().collect { moveNames = it }
        }
    }

    private fun getNextMoves() {
        setProperlyLoadingBasedOnMoveList()
        getMovesJob = viewModelScope.launch {
            _state.run {
                with(getMovesUseCase(value.moves?.size ?: MOVES_INITIAL_OFFSET)) {
                    value = when (this) {
                        is Resource.Success -> value.copy(
                            moves = value.moves?.plus(data!!.moves)
                                ?: emptyList<Move>().plus(data!!.moves),
                            errorMessage = null,
                            isListEndReached = data?.next == null,
                            isInitialLoading = false,
                        )
                        is Resource.Error -> value.copy(
                            errorMessage = message,
                            isInitialLoading = false,
                        )
                    }
                }
            }
        }
    }

    private fun setProperlyLoadingBasedOnMoveList() {
        if (_state.value.moves == null && !_state.value.searching)
            _state.value = _state.value.copy(
                isInitialLoading = true,
                errorMessage = null
            )
        else
            _state.value = _state.value.copy(
                isInitialLoading = false,
                errorMessage = null
            )
    }

    private fun searchMove() {
        val previousQuery = previousSearchQuery
        previousSearchQuery = searchQuery
        if (searchQuery == previousQuery) return
        if (searchQuery.isBlank() && previousQuery.isNotBlank()) {
            _state.value = _state.value.copy(
                moves = null,
                emptySearchResult = false,
                isListEndReached = false
            )
            getNextMoves()
            _state.value = _state.value.copy(searching = false)
            return
        }
        _state.value = _state.value.copy(moves = null)
        _state.value = _state.value.copy(
            searching = true,
            isInitialLoading = false,
            emptySearchResult = false,
            isListEndReached = false,
            errorMessage = null
        )
        getNextSearch()
    }

    private fun getNextSearch() {
        val filteredMoveNames = moveNames.filterAndSortListBy(searchQuery)

        _state.value = _state.value.copy(
            emptySearchResult = filteredMoveNames.isEmpty(),
        )

        getMoveListFrom(
            filteredMoveNames.subList(
                (_state.value.moves?.size ?: 0), getDefaultLimitOrHowManyLeft(
                    filteredMoveNames
                )
            )
        ) {
            _state.value =
                _state.value.copy(
                    isListEndReached = _state.value.moves
                        .isSizeEqualsOrGreaterThan(filteredMoveNames)
                )
        }
    }

    private fun getMoveListFrom(moveNames: List<String>, onJobCompleted: () -> Unit) {
        getMovesJob?.cancel()
        getMovesByNameJob?.cancel()
        getMovesByNameJob = viewModelScope.launch {
            val nextMoves = getMovesByNamesUseCase(moveNames)
            _state.value = _state.value.copy(
                moves = (_state.value.moves
                    ?: emptyList()).plus(nextMoves),
                isInitialLoading = false
            )
        }
        getMovesByNameJob?.invokeOnCompletion { onJobCompleted() }
    }

    private fun getDefaultLimitOrHowManyLeft(filteredMoves: List<String>): Int {
        val actualList = _state.value.moves ?: emptyList()

        return if (canTakeNextItems(actualList.size, filteredMoves.size))
            MOVES_ITEM_LIMIT + actualList.size
        else filteredMoves.size
    }

    private fun canTakeNextItems(actualListSize: Int, allItemsListSize: Int) =
        actualListSize < allItemsListSize - MOVES_ITEM_LIMIT
}