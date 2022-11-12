package com.arix.pokedex.features.common.search_view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.core.errors.ConnectionUnstableError
import com.arix.pokedex.core.errors.Error
import com.arix.pokedex.features.common.search_view.SearchableLazyColumnViewModel
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.SearchBar
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.TextSize
import com.arix.pokedex.theme.WarningColor
import com.arix.pokedex.views.ErrorScreenWithRetryButtonCondensed
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Composable
inline fun <reified T> SearchableLazyColumn(
    searchParams: SearchParams<T>,
    viewModel: SearchableLazyColumnViewModel<T> = getViewModel(qualifier = named(T::class.java.simpleName)) {
        with(searchParams) {
            parametersOf(itemNames, itemsLimit, emptyItem, objectFromNames)
        }
    },
    noinline searchableContent: LazyListScope.(List<T>) -> Unit
) {
    val state = viewModel.state.value
    SearchableLazyColumnContent(state, searchableContent) { viewModel.invokeEvent(it) }
}

@Composable
fun <T> SearchableLazyColumnContent(
    state: SearchableLazyColumnState<T>,
    searchableContent: LazyListScope.(List<T>) -> Unit,
    invokeEvent: (SearchableLazyColumnEvent) -> Unit
) {
    Column {
        SearchBar(
            onValueChange = { invokeEvent(SearchableLazyColumnEvent.SearchByQuery(it)) },
            modifier = Modifier.padding(bottom = 6.dp, start = 10.dp, end = 10.dp)
        )
        if (state.emptySearchResult) NoResultsView()
        ItemListView(state, invokeEvent, searchableContent)
    }
}

@Composable
fun NoResultsView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No results :(")
    }
}

@Composable
private fun <T> ItemListView(
    state: SearchableLazyColumnState<T>,
    invokeEvent: (SearchableLazyColumnEvent) -> Unit,
    searchableContent: LazyListScope.(List<T>) -> Unit
) {
    if (state.error is ConnectionUnstableError)
        UnstableConnectionLabel(state.error)
    LazyColumn {

        if (state.items != null)
            searchableContent(state.items)
        if (!state.isListEndReached)
            item {
                if (!state.items.isNullOrEmpty())
                    LaunchedEffect(key1 = true) {
                        invokeEvent(SearchableLazyColumnEvent.GetNextPage)
                    }

                LoadingOrError(error = state.error, invokeEvent)
            }
    }
}

@Composable
private fun UnstableConnectionLabel(error: ConnectionUnstableError) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = WarningColor)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(error.stringId),
            fontSize = TextSize.small,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun LoadingOrError(
    error: Error?,
    invokeEvent: (SearchableLazyColumnEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        if (error != null && error !is ConnectionUnstableError)
            ErrorScreenWithRetryButtonCondensed(
                onRetryClicked = { invokeEvent(SearchableLazyColumnEvent.GetNextPage) },
                modifier = Modifier.fillMaxWidth()
            )
        else
            CircularProgressIndicator()

    }
}

@Preview
@Composable
private fun SearchableLazyColumnPreview() {
    PokedexTheme {
        Surface {
            Text(text = "All previews for this view are in SearchableLazyColumnPreviews.kt")
        }
    }
}
