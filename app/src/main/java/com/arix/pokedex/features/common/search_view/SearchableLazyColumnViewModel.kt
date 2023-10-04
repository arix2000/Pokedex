package com.arix.pokedex.features.common.search_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.core.Constants.SearchableLazyColumn.INITIAL_OFFSET
import com.arix.pokedex.core.errors.NoConnectionError
import com.arix.pokedex.extensions.*
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumnEvent
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumnState
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchableLazyColumnViewModel<T>(
    private val getItemList: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<T>>
) : ViewModel() {

    private val _state = mutableStateOf(SearchableLazyColumnState<T>())
    val state: State<SearchableLazyColumnState<T>> = _state

    private var searchQuery = ""
        set(value) {
            field = value.toLowerCase(Locale.current).trim()
            searchItem()
        }
    private var previousSearchQuery = ""
    private var getItemListJob: Job? = null
    private var offset: Int = INITIAL_OFFSET

    init {
        getNextItems()
    }

    fun invokeEvent(event: SearchableLazyColumnEvent) {
        when (event) {
            is SearchableLazyColumnEvent.SearchByQuery -> searchQuery = event.query
            is SearchableLazyColumnEvent.GetNextPage -> getNextItems()
        }
    }

    private fun searchItem() {
        offset = 0
        val previousQuery = previousSearchQuery
        previousSearchQuery = searchQuery
        if (searchQuery == previousQuery) return
        _state.run {
            if (searchQuery.isBlank() && previousQuery.isNotBlank()) {
                value = value.copy(
                    items = null,
                    emptySearchResult = false,
                    isListEndReached = false
                )
                getNextItems()
                value = value.copy(searching = false)
                return
            }
            value = value.copy(
                searching = true,
                emptySearchResult = false,
                isListEndReached = false,
                error = null,
                items = null
            )
        }
        getNextItems()
    }

    private fun getNextItems() {
        _state.run {
            getItemListJob?.cancel()
            getItemListJob = viewModelScope.launch {
                val nextMoves = getItemList(offset, searchQuery)
                handleResults(nextMoves)
            }
        }
    }

    private fun handleResults(nextMoves: ApiResponse<Page<T>>) {
        _state.run {
            when {
                nextMoves.isSuccess() -> {
                    nextMoves.data?.run {
                        value = value.copy(
                            items = (value.items ?: emptyList()).plus(nextMoves.data.items),
                            emptySearchResult = items.isEmpty(),
                            isListEndReached = !hasNext,
                            error = null
                        )
                        offset += items.size
                    }
                }

                else -> value = value.copy(
                    error = NoConnectionError(nextMoves.message)
                )
            }
        }
    }
}