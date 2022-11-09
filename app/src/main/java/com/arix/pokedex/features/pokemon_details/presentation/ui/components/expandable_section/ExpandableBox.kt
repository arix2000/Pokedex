package com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arix.pokedex.core.Constants.AnimatedSection.ANIMATIONS_DURATION
import com.arix.pokedex.core.Constants.AnimatedSection.FADE_OUT_DURATION

@Composable
fun ExpandableBox(
    expanded: Boolean,
    content: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = tween(
                durationMillis = ANIMATIONS_DURATION,
                easing = LinearEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(ANIMATIONS_DURATION))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = tween(
                durationMillis = FADE_OUT_DURATION,
                easing = LinearEasing
            )
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(ANIMATIONS_DURATION))
    }
    AnimatedVisibility(
        visible = expanded,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Box(modifier.fillMaxWidth()) {
            content()
        }
    }
}