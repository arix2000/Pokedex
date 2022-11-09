package com.arix.pokedex.features.common.search_view.ui

import com.arix.pokedex.core.errors.Error

data class SearchableLazyColumnState<T>(
    val items: List<T>? = null,
    val error: Error? = null,
    val isListEndReached: Boolean = false,
    val searching: Boolean = false,
    val emptySearchResult: Boolean = false
)