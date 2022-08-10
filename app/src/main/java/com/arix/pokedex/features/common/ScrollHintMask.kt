package com.arix.pokedex.features.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arix.pokedex.core.Constants
import com.arix.pokedex.theme.BlackLight

@Composable
fun BoxScope.ScrollHintMask(isVisible: Boolean) {
    val enterSlide = remember {
        fadeIn(animationSpec = tween(durationMillis = Constants.PokemonDetailsScreen.VARIETIES_SCROLL_HINT_ANIM_DURATION))
    }
    val exitSlide = remember {
        fadeOut(animationSpec = tween(durationMillis = Constants.PokemonDetailsScreen.VARIETIES_SCROLL_HINT_ANIM_DURATION))
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = enterSlide,
        exit = exitSlide,
        modifier = Modifier.align(Alignment.CenterEnd)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            BlackLight,
                        )
                    )
                )
                .size(150.dp, 240.dp)
        )
    }
}