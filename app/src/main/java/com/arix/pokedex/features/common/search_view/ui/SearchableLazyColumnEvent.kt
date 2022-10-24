package com.arix.pokedex.features.common.search_view.ui

sealed class SearchableLazyColumnEvent {
    class SearchByQuery(val query: String): SearchableLazyColumnEvent()

    object GetNextPage: SearchableLazyColumnEvent()
}
