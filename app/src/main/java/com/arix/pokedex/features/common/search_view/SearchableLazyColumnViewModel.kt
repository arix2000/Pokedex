package com.arix.pokedex.features.common.search_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.core.Constants.SearchableLazyColumn.INITIAL_OFFSET
import com.arix.pokedex.core.errors.ConnectionUnstableError
import com.arix.pokedex.core.errors.NoConnectionError
import com.arix.pokedex.extensions.*
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumnEvent
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumnState
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchableLazyColumnViewModel<T>(
    private val itemNames: List<String>,
    private val itemsLimit: Int,
    private val emptyItem: T,
    private val objectFromNames: suspend (List<String>) -> List<ApiResponse<T>>
) : ViewModel() {

    private val _state = mutableStateOf(SearchableLazyColumnState<T>())
    val state: State<SearchableLazyColumnState<T>> = _state

    private var searchQuery = ""
        set(value) {
            field = value.toLowerCase(Locale.current).trim()
            searchItem()
        }
    private var previousSearchQuery = ""
    private var getItemsByNameJob: Job? = null

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
        val filteredItemNames = itemNames.filterAndSortListBy(searchQuery)
        _state.run {
            value = value.copy(emptySearchResult = filteredItemNames.isEmpty(), error = null)

            getItemListFrom(
                filteredItemNames.subList(
                    (value.items?.size ?: INITIAL_OFFSET), getDefaultLimitOrHowManyLeft(
                        filteredItemNames
                    )
                )
            ) {
                value = value.copy(
                    isListEndReached = value.items.isSizeEqualsOrGreaterThan(filteredItemNames)
                )
            }
        }
    }

    private fun getItemListFrom(itemNames: List<String>, onJobCompleted: () -> Unit) {
        getItemsByNameJob?.cancel()
        getItemsByNameJob = viewModelScope.launch {
            val nextMoves = objectFromNames(itemNames)
            handleResults(nextMoves)
        }
        getItemsByNameJob?.invokeOnCompletion { onJobCompleted() }
    }

    private fun handleResults(nextMoves: List<ApiResponse<T>>) {
        _state.run {
            when {
                nextMoves.ifAllErrors() -> value = value.copy(
                    error = NoConnectionError(nextMoves.firstOrNull()?.message)
                )
                nextMoves.ifAllSuccess() -> value = value.copy(
                    items = (value.items ?: emptyList()).plus(nextMoves.map { it.data!! }),
                    error = null
                )
                nextMoves.ifAnyError() -> value = value.copy(
                    items = (value.items ?: emptyList()).plus(nextMoves.map {
                        it.data ?: emptyItem
                    }),
                    error = ConnectionUnstableError(nextMoves.firstOrNull { it is ApiResponse.Error }?.message)
                )
            }
        }
    }

    private fun getDefaultLimitOrHowManyLeft(filteredItems: List<String>): Int {
        val actualList = _state.value.items ?: emptyList()

        return if (canTakeNextItems(actualList.size, filteredItems.size))
            itemsLimit + actualList.size
        else filteredItems.size
    }

    private fun canTakeNextItems(actualListSize: Int, allItemsListSize: Int) =
        actualListSize < allItemsListSize - itemsLimit
}