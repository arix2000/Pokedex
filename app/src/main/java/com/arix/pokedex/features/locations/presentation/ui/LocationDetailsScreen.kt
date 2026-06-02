package com.arix.pokedex.features.locations.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.core.Constants.LocationsScreenConst.AREA_LIMIT
import com.arix.pokedex.core.Constants.LocationsScreenConst.SINGLE_AREA_SUFFIX
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.extensions.toSentenceCase
import com.arix.pokedex.features.locations.domain.model.details.UiLocationArea
import com.arix.pokedex.features.locations.presentation.LocationViewModel
import com.arix.pokedex.features.locations.presentation.ui.composables.LocationRegionCard
import com.arix.pokedex.features.move_details.presentation.ui.components.GridView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section.ExpandableSection
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.PokemonListItem
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.arix.pokedex.views.FadingHorizontalDivider
import org.koin.androidx.compose.get
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
fun LocationsDetailsScreenContent(state: LocationState, navigator: Navigator = get()) {
    val locationDetails = state.locationDetails!!
    val locationAreas = state.uiLocationAreas
    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = locationDetails.name.toSentenceCase(),
                overflow = TextOverflow.Ellipsis,
                fontSize = FontSizes.large,
                modifier = Modifier.padding(8.dp)
            )
            LocationRegionCard(locationDetails.regionName, locationDetails.getRegionColor())
            FadingHorizontalDivider(Modifier.padding(vertical = 12.dp))
            Text(stringResource(R.string.pokemon_encounters))
            locationAreas.forEach { area ->
                Spacer(Modifier.height(12.dp))
                PokemonGridSection(
                    area,
                    area.name.removePrefix(locationDetails.name)
                        .trim() != SINGLE_AREA_SUFFIX && locationAreas.size != 1,
                    navigator
                )
            }
        }
    }
}

@Composable
private fun PokemonGridSection(
    area: UiLocationArea,
    isSingleArea: Boolean,
    navigator: Navigator
) {
    var showMoreButton: Boolean by remember { mutableStateOf(area.pokemonList.size > AREA_LIMIT) }
    var shouldShowAllItems: Boolean by remember { mutableStateOf(false) }

    val sectionList = if (!showMoreButton || shouldShowAllItems)
        area.pokemonList
    else
        area.pokemonList.take(AREA_LIMIT)

    ExpandableSectionIf(isSingleArea, area.name.toSentenceCase()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GridView(data = sectionList, cells = 2) { pokemon ->
                PokemonListItem(
                    pokemonItem = pokemon, modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                ) {
                    navigator.goToPokemonDetails(pokemon.name)
                }
            }
            if (showMoreButton)
                Box(
                    Modifier
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .clickable {
                            shouldShowAllItems = true; showMoreButton = false
                        }) {
                    Row {
                        Text(
                            text = stringResource(
                                R.string.show_all,
                                area.pokemonList.count()
                            ).uppercase(),
                            fontSize = FontSizes.normal
                        )
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "keyDown/up",
                        )
                    }
                }
        }
    }
}

@Composable
private fun ExpandableSectionIf(
    wrapInExpandableSection: Boolean,
    title: String,
    child: @Composable () -> Unit
) {
    if (wrapInExpandableSection)
        ExpandableSection(title, expandedInitially = true) {
            child()
        }
    else
        child()
}

@Preview
@Composable
private fun LocationDetailsScreenPreview() {
    val mockResourceReader = MockResourceReader(LocalContext.current)
    PokedexTheme {
        Surface {
            Box {
                Column {
                    LocationsDetailsScreenContent(
                        LocationState(
                            locationDetails = mockResourceReader.getLocationDetailsMock(),
                            uiLocationAreas = mockResourceReader.getLocationAreasMock()
                        ),
                        navigator = Navigator()
                    )
                }
            }
        }
    }
}