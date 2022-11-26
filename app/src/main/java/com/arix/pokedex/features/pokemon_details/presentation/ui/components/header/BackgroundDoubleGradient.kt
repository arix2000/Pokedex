package com.arix.pokedex.features.pokemon_details.presentation.ui.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.pokemon_list.domain.model.details.TypeX
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun BoxScope.BackgroundGradientBasedOn(
    types: List<Type>,
    specs: BackgroundGradientSpecs = BackgroundGradientSpecs.DEFAULT
) {
    if (types.size > 1)
        BackgroundDoubleCornerGradient(types = types, specs)
    else
        BackgroundGradientFromTop(type = types.first(), specs)

}

@Composable
fun BoxScope.BackgroundDoubleCornerGradient(
    types: List<Type>,
    specs: BackgroundGradientSpecs
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(specs.height)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        types[0].getTypeColor(specs.alpha),
                        Color.Transparent,
                    ),
                    center = Offset(x = 0F, y = 0F),
                    radius = specs.radius
                ),
            )
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopEnd)
            .height(specs.height)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        types[1].getTypeColor(specs.alpha),
                        Color.Transparent,
                    ),
                    center = Offset(x = Float.POSITIVE_INFINITY, y = 0F),
                    radius = specs.radius
                ),
            )
    )
}

@Composable
fun BackgroundGradientFromTop(type: Type, specs: BackgroundGradientSpecs) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(specs.height)
            .scale(1.4f, 1f)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        type.getTypeColor(specs.alpha),
                        Color.Transparent,
                    ),
                    center = Offset(x = 532F, y = 0F),
                    radius = specs.radius / 1.2f
                ),
            )
    )
}

@Preview
@Composable
fun BackgroundDoubleCornerGradientPreview() {
    PokedexTheme {
        Surface {
            Box(modifier = Modifier.fillMaxWidth()) {
                BackgroundGradientBasedOn(
                    types = listOf(
                        Type(0, TypeX("grass", "")),
                        Type(0, TypeX("fire", ""))
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun BackgroundGradientFromTopPreview() {
    PokedexTheme {
        Surface {
            Box(modifier = Modifier.fillMaxWidth()) {
                BackgroundGradientBasedOn(
                    types = listOf(
                        Type(0, TypeX("grass", ""))
                    )
                )
            }
        }
    }
}