package com.arix.pokedex.views

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Shape
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun ShimmerAnimatedBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    val shimmerColors = listOf(
        Color.DarkGray.copy(alpha = 0.4f),
        Color.DarkGray.copy(alpha = 0.2f),
        Color.DarkGray.copy(alpha = 0.4f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = -400f,
        targetValue = 1500f,
        animationSpec = infiniteRepeatable(
            initialStartOffset = StartOffset(
                offsetMillis = 250,
                offsetType = StartOffsetType.FastForward
            ),
            animation = tween(
                durationMillis = 600,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )
    Box(modifier = Modifier.background(backgroundColor, shape)) {
        BoxWithAnimated(brush = brush, modifier)
    }
}

@Composable
private fun BoxWithAnimated(brush: Brush, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(brush, shape = RoundedCornerShape(12.dp))
    )
}

@Composable
@Preview
private fun ShimmerBoxPreview() {
    PokedexTheme {
        Surface() {
            ShimmerAnimatedBox(modifier = Modifier.size(100.dp))
        }
    }
}