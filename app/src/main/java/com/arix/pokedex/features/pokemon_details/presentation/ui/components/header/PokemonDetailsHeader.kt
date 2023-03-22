package com.arix.pokedex.features.pokemon_details.presentation.ui.components.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
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
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.TypesSection
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.theme.WhiteA50
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.FadingHorizontalDivider

@Composable
fun PokemonDetailsHeader(
    pokemonDetails: PokemonDetails,
    species: PokemonSpecies,
    onImageClicked: (url: String) -> Unit
) {
    var imageModel by remember {
        mutableStateOf(pokemonDetails.sprites.front_default)
    }
    var isImageLoading by remember { mutableStateOf(true) }

    Box {
        BackgroundGradientBasedOn(pokemonDetails.types)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Box(Modifier.height(250.dp)) {
                AsyncImage(
                    model = imageModel,
                    contentDescription = pokemonDetails.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .align(Alignment.Center)
                        .clickable(
                            indication = rememberRipple(bounded = false, radius = 130.dp),
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onImageClicked(imageModel) },
                    error = painterResource(id = R.drawable.pokemon_not_found_image),
                    onLoading = { isImageLoading = true },
                    onSuccess = { isImageLoading = false },
                )
                if (isImageLoading)
                    DefaultProgressIndicatorScreen(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
            }
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
        ShinyToggleButton(imageModel, pokemonDetails, onClick = { imageModel = it })
    }
}

@Composable
fun ShinyToggleButton(
    imageModel: String,
    pokemonDetails: PokemonDetails,
    onClick: (String) -> Unit
) {
    val isShinyShowed = imageModel == pokemonDetails.sprites.front_shiny
    Button(
        contentPadding = PaddingValues(
            top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp
        ),
        shape = Shapes.large,
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .padding(start = 9.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = pokemonDetails.types.first().getTypeColor()
        ),
        onClick = {
            onClick(
                if (isShinyShowed)
                    pokemonDetails.sprites.front_default
                else pokemonDetails.sprites.front_shiny
            )
        }) {
        Text(text = if (isShinyShowed) "Normal" else "Shiny")
    }
}

@Preview
@Composable
fun PokemonDetailsHeaderPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
    val species = remember { MockResourceReader(context).getPokemonSpeciesMock() }
    PokedexTheme {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                PokemonDetailsHeader(pokemonDetails, species) {}
            }
        }
    }
}