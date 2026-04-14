package com.arix.pokedex.features.locations.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.locations.domain.model.details.LocationDetails
import com.arix.pokedex.features.locations.presentation.LocationViewModel
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationDetailsScreen(
    id: Int, viewModel: LocationViewModel = getViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.fetchLocationDetails(id)
    }
    when {
        state.locationDetails != null -> {
            LocationsDetailsScreenContent(location = state.locationDetails)
        }

        state.isLoading -> DefaultProgressIndicatorScreen()
        state.errorMessage != null -> ErrorScreenWithRetryButton {
            viewModel.fetchLocationDetails(id)
        }
    }

}

@Composable
fun LocationsDetailsScreenContent(location: LocationDetails) {
    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(Gson().toJson(location))
    }
}

@Preview
@Composable
private fun LocationDetailsScreenPreview() {
    PokedexTheme {
        Surface {
            LocationsDetailsScreenContent(LocationDetails("", "", emptyList()))
        }
    }
}