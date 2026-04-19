package com.arix.pokedex.features.locations.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.locations.presentation.LocationViewModel
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.arix.pokedex.views.FadingHorizontalDivider
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
        state.locationDetails != null && state.uiLocationAreas.isNotEmpty() -> {
            LocationsDetailsScreenContent(state = state)
        }

        state.isLoading -> DefaultProgressIndicatorScreen()
        state.errorMessage != null -> ErrorScreenWithRetryButton {
            viewModel.fetchLocationDetails(id)
        }
    }

}

@Composable
fun LocationsDetailsScreenContent(state: LocationState) {
    val locationDetails = state.locationDetails!!
    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column {
            Text(state.locationDetails.name)
            FadingHorizontalDivider()
        }
    }
}

@Preview
@Composable
private fun LocationDetailsScreenPreview() {
    val mockResourceReader = MockResourceReader(LocalContext.current)
    PokedexTheme {
        Surface {
            LocationsDetailsScreenContent(
                LocationState(
                    locationDetails = mockResourceReader.getLocationDetailsMock(),
                    uiLocationAreas = mockResourceReader.getLocationAreasMock()
                )
            )
        }
    }
}