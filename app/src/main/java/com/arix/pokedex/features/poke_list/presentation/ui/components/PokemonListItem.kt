package com.arix.pokedex.features.poke_list.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.extensions.clickableOnceInTime
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.details.Type
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.theme.TextSize
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.FadingHorizontalDivider
import com.arix.pokedex.views.shimmer_effect.ShimmerAnimatedBox

@Composable
fun PokemonListItem(
    pokemonDetails: PokemonDetails,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val isPreview = LocalInspectionMode.current
    var isImageLoading by remember { mutableStateOf(!isPreview) }
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clip(Shapes.medium)
            .background(brush = getBrushBasedOn(isImageLoading, pokemonDetails.types))
            .clickableOnceInTime(enabled = onClick != null) {
                onClick?.invoke()
            }
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
                onSuccess = { isImageLoading = false },
                onError = { isImageLoading = false },
                error = painterResource(id = R.drawable.pokemon_not_found_image)
            )
            FadingHorizontalDivider()
            Text(
                text = pokemonDetails.name.capitalize(LocaleList.current),
                fontSize = TextSize.large,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            TypesSection(pokemonDetails.types, spacing = 2.dp, itemFontSize = TextSize.small)
            Spacer(modifier = Modifier.height(10.dp))
        }
        ShowShimmerIf(modifier = modifier, isImageLoading)
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
private fun ShowShimmerIf(modifier: Modifier = Modifier, imageLoading: Boolean) {
    if (imageLoading)
        ShimmerAnimatedBox(modifier = modifier)
}

@Preview
@Composable
fun PokemonItemPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
    PokedexTheme {
        Surface() {
            PokemonListItem(pokemonDetails, Modifier.width(180.dp))
        }
    }
}