package com.arix.pokedex.features.poke_list.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.details.Type
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.ShimmerAnimatedBox
import com.google.gson.Gson

@Composable
fun PokemonItem(pokemonDetails: PokemonDetails, modifier: Modifier = Modifier) {
    val isPreview = LocalInspectionMode.current
    var isImageLoading by remember { mutableStateOf(!isPreview) }
    Box(
        modifier = Modifier
            .padding(5.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                brush = getBrushBasedOn(
                    isImageLoading,
                    pokemonDetails.types
                )
            )
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = pokemonDetails.sprites.other.official_artwork.front_default,
                contentDescription = pokemonDetails.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.scyther),
                onSuccess = { isImageLoading = false }
            )
            FadingDivider()
            Text(text = pokemonDetails.name.capitalize(LocaleList.current), fontSize = 20.sp)
            TypesSection(pokemonDetails.types)
            Spacer(modifier = Modifier.height(10.dp))
        }
        ShowShimmerIf(isImageLoading)
    }
}

private fun getBrushBasedOn(isImageLoading: Boolean, types: List<Type>): Brush {
    val colorList = mutableListOf(types.firstOrNull()?.getTypeColor() ?: Color.Black, Color.Black)
    return if (!isImageLoading) Brush.linearGradient(
        colorList,
        end = Offset(300F, 300F)
    ) else Brush.linearGradient(
        listOf(Color.Black, Color.Black)
    )
}

@Composable
fun ShowShimmerIf(imageLoading: Boolean) {
    if (imageLoading)
        ShimmerAnimatedBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
}

@Composable
private fun FadingDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.7.dp)
            .padding(horizontal = 10.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Gray,
                        Color.Transparent
                    )
                )
            )
    )
}

@Preview
@Composable
fun PokemonItemPreview() {
    PokedexTheme {
        Surface() {
            val pokemonDetailsJson =
                LocalContext.current.resources.openRawResource(R.raw.pokemon_details_example)
                    .reader()
                    .readText()
            val pokemonDetails: PokemonDetails =
                Gson().fromJson(pokemonDetailsJson, PokemonDetails::class.java)
            PokemonItem(pokemonDetails, Modifier.width(150.dp))
        }
    }
}