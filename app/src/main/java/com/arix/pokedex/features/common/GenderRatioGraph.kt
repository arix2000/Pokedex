package com.arix.pokedex.features.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.theme.GanderFemaleColor
import com.arix.pokedex.theme.GanderMaleColor
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun GenderRatioGraph(ratio: Float, modifier: Modifier = Modifier) {
    Surface(shape = CircleShape, modifier = modifier) {
        LinearProgressIndicator(
            progress = ratio,
            color = GanderMaleColor,
            backgroundColor = GanderFemaleColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
        )
    }
}

@Preview
@Composable
fun GenderRatioGraphPreview() {
    PokedexTheme {
        Surface {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                GenderRatioGraph(0.75f)
            }
        }
    }
}