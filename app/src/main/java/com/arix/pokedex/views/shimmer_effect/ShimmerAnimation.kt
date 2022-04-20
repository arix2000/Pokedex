package com.arix.pokedex.views.shimmer_effect

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun getShimmerAnimatedBrush(): Brush {
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

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )
}