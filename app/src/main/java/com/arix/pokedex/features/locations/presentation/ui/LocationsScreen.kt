package com.arix.pokedex.features.locations.presentation.ui

import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumn
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.presentation.LocationViewModel
import com.arix.pokedex.features.locations.presentation.ui.composables.LocationListItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.ApiResponse
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationsScreen(
    viewModel: LocationViewModel = getViewModel()
) {
    LocationsScreenContent { offset, searchQuery -> viewModel.getLocations(offset, searchQuery) }
}

@Composable
fun LocationsScreenContent(
    navigator: Navigator = get(),
    getLocationList: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<LocationItem>>

) {
    SearchableLazyColumn(
        searchParams = SearchParams(getLocationList),
        searchableContent = { locations ->
            items(locations, key = { it.id }) { location ->
                LocationListItem(location) { locationId -> navigator.goToLocationDetails(locationId.toString()) }
            }
        }
    )
}

@Preview
@Composable
private fun LocationsScreenPreview() {
    PokedexTheme {
        Surface {
            Text(text = "All previews for this view are in the SearchableLazyColumnPreviews.kt")
        }
    }
}