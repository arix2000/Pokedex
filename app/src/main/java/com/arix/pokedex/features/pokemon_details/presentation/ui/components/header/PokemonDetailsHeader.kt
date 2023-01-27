package com.arix.pokedex.features.pokemon_details.presentation.ui.components.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.TypesSection
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.WhiteA50
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.FadingHorizontalDivider
import org.koin.androidx.compose.get

@Composable
fun PokemonDetailsHeader(
    pokemonDetails: PokemonDetails,
    species: PokemonSpecies,
    navigator: Navigator = get()
) {
    Box {
        BackgroundGradientBasedOn(pokemonDetails.types)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            AsyncImage(
                model = pokemonDetails.sprites.other.official_artwork.front_default,
                contentDescription = pokemonDetails.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .clickable(
                        indication = rememberRipple(bounded = false, radius = 130.dp),
                        interactionSource = remember { MutableInteractionSource() }
                    ) { navigator.goToImageFullScreen() },
                placeholder = painterResource(id = R.drawable.scyther),
                error = painterResource(id = R.drawable.pokemon_not_found_image)
            )
            FadingHorizontalDivider(modifier = Modifier.padding(top = 12.dp, bottom = 8.dp))
            Text(
                text = pokemonDetails.name.capitalize(LocaleList.current),
                fontSize = FontSizes.extraLarge
            )
            Text(text = species.getGeneraText(), color = WhiteA50)
            TypesSection(
                types = pokemonDetails.types,
                spacing = 8.dp,
                itemFontSize = FontSizes.medium,
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(90.dp)
            )
        }
    }
}

@Preview
@Composable
fun PokemonDetailsHeaderPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
    val species = remember { MockResourceReader(context).getPokemonSpeciesMock() }
    PokedexTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            PokemonDetailsHeader(pokemonDetails, species, Navigator())
        }
    }
}