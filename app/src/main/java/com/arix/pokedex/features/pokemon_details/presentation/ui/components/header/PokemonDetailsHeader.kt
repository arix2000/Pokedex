package com.arix.pokedex.features.pokemon_details.presentation.ui.components.header

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.presentation.ui.components.TypesSection
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.TextSize
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.FadingHorizontalDivider

@Composable
fun PokemonDetailsHeader(pokemonDetails: PokemonDetails, modifier: Modifier = Modifier) {
    Box {
        BackgroundGradientBasedOn(pokemonDetails.types)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(top = 20.dp)
        ) {
            AsyncImage(
                model = pokemonDetails.sprites.other.official_artwork.front_default,
                contentDescription = pokemonDetails.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth(0.5f),
                placeholder = painterResource(id = R.drawable.scyther),
                error = painterResource(id = R.drawable.pokemon_not_found_image)
            )
            FadingHorizontalDivider(modifier = Modifier.padding(top = 12.dp, bottom = 8.dp))
            Text(
                text = pokemonDetails.name.capitalize(LocaleList.current),
                fontSize = TextSize.extraLarge
            )
            TypesSection(
                types = pokemonDetails.types,
                spacing = 8.dp,
                itemFontSize = TextSize.medium,
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
    PokedexTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PokemonDetailsHeader(pokemonDetails)
        }
    }
}