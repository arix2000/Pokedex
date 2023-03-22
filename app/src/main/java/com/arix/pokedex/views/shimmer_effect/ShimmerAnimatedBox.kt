package com.arix.pokedex.views.shimmer_effect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes

@Composable
fun ShimmerAnimatedBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    shape: Shape = Shapes.medium
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape)
            .background(getShimmerAnimatedBrush(), shape = Shapes.medium)
    )
}

@Composable
@Preview
private fun ShimmerBoxPreview() {
    PokedexTheme {
        Surface {
            ShimmerAnimatedBox(modifier = Modifier.size(100.dp))
        }
    }
}