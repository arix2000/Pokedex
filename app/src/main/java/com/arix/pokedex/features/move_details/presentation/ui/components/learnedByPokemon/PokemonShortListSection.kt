package com.arix.pokedex.features.move_details.presentation.ui.components.learnedByPokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.features.move_details.presentation.MoveDetailsViewModel
import com.arix.pokedex.features.move_details.presentation.ui.MoveDetailsEvent
import com.arix.pokedex.features.move_details.presentation.ui.components.GridView
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.PokemonListItem
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonShortListSection(
    pokemonNames: List<String>,
    onSeeAllClicked: (List<String>) -> Unit,
    viewModel: MoveDetailsViewModel = getViewModel(),
    title: String = stringResource(R.string.can_be_learned_by_title),
    showMoreButton: Boolean = true
) {
    val state = viewModel.learnedByPokemonState.value
    when {
        state.pokemonList != null -> PokemonShortListSection(
            state.pokemonList,
            onSeeAllClicked,
            pokemonNames,
            title,
            showMoreButton
        )

        state.isLoading -> DefaultProgressIndicatorScreen(modifier = Modifier.height(100.dp))
        state.errorMessage != null -> ErrorScreenWithRetryButton {
            viewModel.invokeEvent(MoveDetailsEvent.LoadLearnedBySection(pokemonNames))
        }
    }
}

@Composable
fun PokemonShortListSection(
    pokemonList: List<PokemonDetails>,
    onSeeAllClicked: (pokemonNames: List<String>) -> Unit,
    pokemonNames: List<String>,
    titleRes: String,
    showMoreButton: Boolean,
    navigator: Navigator = get(),
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = titleRes,
            fontSize = FontSizes.large,
            fontWeight = FontWeight.SemiBold
        )
        if (pokemonNames.size > 6)
            Button(
                onClick = { onSeeAllClicked(pokemonNames); },
                contentPadding = PaddingValues(
                    top = 3.dp, bottom = 3.dp, start = 10.dp, end = 5.dp
                ),
                shape = Shapes.large,
                modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            ) {
                Text(
                    text = stringResource(R.string.show_all, pokemonNames.size).uppercase(),
                    fontSize = FontSizes.minimum
                )
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "",
                    tint = Color.White
                )
            }
    }
    GridView(data = pokemonList, cells = 2) {
        PokemonListItem(
            pokemonItem = it, modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            navigator.goToPokemonDetails(it.name)
        }
    }
}

@Preview
@Composable
private fun LearnedByPokemonSectionPreview() {
    val context = LocalContext.current
    val pokemon = remember { MockResourceReader(context).getPokemonDetailsMock() }
    val pokeList = mutableListOf<PokemonDetails>()
    val pokeNames = mutableListOf<String>()
    repeat(6) { pokeList.add(pokemon) }
    repeat(20) { pokeNames.add("pokemon") }
    PokedexTheme {
        Surface {
            Column {
                PokemonShortListSection(
                    pokeList,
                    {},
                    pokeNames,
                    titleRes = stringResource(R.string.show_all),
                    showMoreButton = true,
                    Navigator()
                )
            }
        }
    }
}