package com.arix.pokedex.features.pokemon_details.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.R
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.extensions.hasOneItem
import com.arix.pokedex.extensions.isPreview
import com.arix.pokedex.features.common.AppTopBar
import com.arix.pokedex.features.common.buttons.ShowAllWithCountButton
import com.arix.pokedex.features.items.presentation.ui.components.ItemBottomSheetContent
import com.arix.pokedex.features.limited_list.domain.LimitedListType
import com.arix.pokedex.features.locations.presentation.ui.composables.LocationListItem
import com.arix.pokedex.features.moves.presentation.ui.components.MoveListItem
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_details.domain.model.species.Variety
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.AbilityListItem
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.base_stats.BaseStatsView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.details.DetailsView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain.EvolutionChainView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section.ExpandableSection
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.forms.VariantsView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.full_screen_image_dialog.FullScreenImageDialog
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.header.PokemonDetailsHeader
import com.arix.pokedex.features.pokemon_list.domain.model.details.Ability
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.theme.BlackSoft
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

/**
 * TODO PLAN
 *
 * Base Stats sections based on [PokemonDetails.stats] - DONE
 * Abilities - [PokemonDetails.abilities] we need to fetch description and show in the existing section - DONE
 *
 * add cry button to hear pokemon cry, it should be placed under [PokemonDetails.cries] - DONE
 *
 * location encounters - pokemon/[id]/encounters link is already in details, just fetch and get from
 * list api limited list of locations (it should work on backend),
 * we need to show like 10 of theme and rest should stay behind show all button, on dedicated screen,
 * like in [com.arix.pokedex.features.move_details.presentation.ui.screens.MoveDetailsScreen] screen
 * and its [com.arix.pokedex.features.limited_list.presentation.LimitedListScreen] screen - DONE
 *
 * Do the same for [PokemonDetails.moves] but here we have ready to sent list - DONE
 *
 * In [EvolutionChainSection] when user clicks item it should load item details in bottom sheet
 * **/

@Composable
fun PokemonDetailsScreen(
    pokemonName: String,
    viewModel: PokemonDetailsViewModel
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = pokemonName) {
        viewModel.invokeEvent(PokemonDetailsEvent.GetInitialData(pokemonName))
    }
    if (state.isLoading)
        DefaultProgressIndicatorScreen()
    else if (state.pokemonDetails == null || state.species == null || state.evolutionChain == null)
        Text(text = "Error: ${state.errorMessage}")
    else
        PokemonDetailsScreenContent(
            state.pokemonDetails,
            state.species,
            state.evolutionChain
        )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonDetailsScreenContent(
    pokemonDetails: PokemonDetails,
    species: PokemonSpecies,
    evolutionChain: PokemonEvolutionChain,
    navigator: Navigator = get()
) {
    val isDialogShowed = remember { mutableStateOf(false) }
    var clickedImageUrl by remember { mutableStateOf(pokemonDetails.sprites.front_default) }

    var clickedItemId: Int by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = Shapes.bottomSheet,
        sheetContent = { ItemBottomSheetContent(clickedItemId) },
        sheetBackgroundColor = BlackSoft,
        scrimColor = MaterialTheme.colors.surface.copy(0.52f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                PokemonDetailsHeader(pokemonDetails, species, onImageClicked = {
                    clickedImageUrl = it
                    isDialogShowed.value = true
                })
                ExpandableSection(
                    title = stringResource(R.string.details_title),
                ) {
                    DetailsView(pokemonDetails, species)
                }
                ExpandableSection(title = stringResource(R.string.pokemon_description)) {
                    Text(text = species.getDescription())
                }
                EvolutionChainSection(pokemonDetails, evolutionChain, onItemClicked = {
                    clickedItemId = it
                    coroutineScope.launch { modalSheetState.show() }
                })
                ExpandableSection(
                    title = stringResource(R.string.stats_title),
                ) {
                    BaseStatsView(pokemonDetails.stats)
                }
                if (!(species.varieties.hasOneItem() && species.varieties.first().is_default)) {
                    VarietiesSection(pokemonDetails.name, species.varieties)
                }
                ExpandableSection(title = stringResource(R.string.moves_label)) {
                    MovesSectionContent(pokemonDetails, navigator)
                }
                ExpandableSection(title = stringResource(R.string.locations_label)) {
                    LocationsSectionContent(pokemonDetails, navigator)
                }
                AbilitiesSection(pokemonDetails.abilities)
                Spacer(Modifier.height(64.dp))
            }
        }
    }
    FullScreenImageDialog(
        isDialogShowed = isDialogShowed,
        imageUrl = clickedImageUrl
    )
}

@Composable
fun MovesSectionContent(
    pokemonDetails: PokemonDetails,
    navigator: Navigator
) {
    val context = LocalContext.current
    val movesLimited = pokemonDetails.moves
    val allMovesNames = pokemonDetails.allMovesNames
    Column(horizontalAlignment = Alignment.End) {
        for (move in movesLimited.withIndex()) {
            MoveListItem(
                move.value,
                modifier = Modifier.padding(3.dp)
            ) { moveId -> navigator.goToMoveDetails(moveId) }
        }
        if (allMovesNames.size > 6)
            ShowAllWithCountButton(allMovesNames.size, onClicked = {
                navigator.goToLimitedList(
                    allMovesNames,
                    context.getString(
                        R.string.pokemon_can_learn_moves_page_title,
                        pokemonDetails.name
                    ),
                    LimitedListType.MOVES
                )
            })
    }
}

@Composable
fun LocationsSectionContent(
    pokemonDetails: PokemonDetails,
    navigator: Navigator
) {
    val context = LocalContext.current
    val locationsLimited = pokemonDetails.locations
    val allLocationsNames = pokemonDetails.allLocationsNames
    Column(horizontalAlignment = Alignment.End) {
        for (location in locationsLimited.withIndex()) {
            LocationListItem(
                modifier = Modifier.padding(3.dp),
                location = location.value,
                onClick = { locationId ->
                    navigator.goToLocationDetails(locationId.toString())
                }
            )
        }
        if (allLocationsNames.size > 6)
            ShowAllWithCountButton(allLocationsNames.size, onClicked = {
                navigator.goToLimitedList(
                    allLocationsNames,
                    context.getString(
                        R.string.pokemon_can_be_encountered_page_title,
                        pokemonDetails.name
                    ),
                    LimitedListType.LOCATIONS
                )
            })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun EvolutionChainSection(
    pokemonDetails: PokemonDetails,
    evolutionChain: PokemonEvolutionChain,
    onItemClicked: (Int) -> Unit
) {
    ExpandableSection(
        title = stringResource(R.string.evolution_chain_title),
        expandedInitially = true
    ) {
        if (!isPreview())
            EvolutionChainView(pokemonDetails, evolutionChain,onItemClicked)
        else DefaultProgressIndicatorScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
private fun AbilitiesSection(abilities: List<Ability>) {
    ExpandableSection(title = stringResource(R.string.abilities_title)) {
        if (abilities.isEmpty())
            Text(text = stringResource(R.string.none))
        else
            Column {
                abilities.forEach {
                    AbilityListItem(ability = it)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
    }
}

@Composable
private fun VarietiesSection(
    rootPokemonDetailsName: String,
    varieties: List<Variety>,
) {
    ExpandableSection(
        title = stringResource(R.string.pokemon_details_variants)
    ) {
        if (!isPreview())
            VariantsView(rootPokemonDetailsName, varieties)
    }
}

@Preview
@Composable
private fun PokemonDetailsScreenContentPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
    val pokemonSpecies = remember { MockResourceReader(context).getPokemonSpeciesMock() }
    val pokemonEvolutionChain =
        remember { MockResourceReader(context).getPokemonEvolutionChainMock() }
    PokedexTheme {
        Surface {
            Column {
                AppTopBar(
                    navController = rememberNavController(),
                    showBackButton = true,
                    scaffoldState = rememberScaffoldState(),
                )
                PokemonDetailsScreenContent(
                    pokemonDetails,
                    pokemonSpecies,
                    pokemonEvolutionChain,
                    Navigator()
                )
            }
        }
    }
}