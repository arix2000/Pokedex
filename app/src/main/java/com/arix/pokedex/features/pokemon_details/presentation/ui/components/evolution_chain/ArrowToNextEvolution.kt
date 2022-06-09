package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Primary

@Composable
fun ArrowToNextEvolution(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(16.dp)
            .width(80.dp)
            .padding(2.dp)
    ) {
        val strokeWidth = remember { 6F }
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            drawPath(
                color = color,
                path = Path().apply {
                    moveTo(0f, height)
                    quadraticBezierTo(width / 2, -height, width, height)
                },
                style = Stroke(strokeWidth, cap = StrokeCap.Round),
            )
            drawLine(
                start = Offset(x = width, y = height),
                end = Offset(x = width - 20, y = height-30),
                color = color,
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
            drawLine(
                start = Offset(x = width, y = height),
                end = Offset(x = width - 34, y = height),
                color = color,
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }

}

@Preview
@Composable
fun ArrowToNextEvolutionPreview() {
    PokedexTheme {
        Surface {
            ArrowToNextEvolution(Primary)
        }
    }
}