package com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import com.arix.pokedex.R
import com.arix.pokedex.core.Constants.AnimatedSection.ANIMATIONS_DURATION

@Composable
fun RotatingIcon(rotated: Boolean, title: String) {
    val angle: Float by animateFloatAsState(
        targetValue = if (!rotated) 0f else 180f,
        animationSpec = tween(durationMillis = ANIMATIONS_DURATION, easing = LinearEasing)
    )
    Icon(
        imageVector = Icons.Rounded.KeyboardArrowDown,
        contentDescription = stringResource(id = R.string.dropdown_arrow_desc, title),
        modifier = Modifier.rotate(angle)
    )
}