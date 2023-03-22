package com.arix.pokedex.features.common.text

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.arix.pokedex.core.Constants.AnimatedSection.ANIMATIONS_DURATION
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    maxLines: Int = 3,
    duration: Int = ANIMATIONS_DURATION,
    textAlign: TextAlign = TextAlign.Center
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier
        .animateContentSize(tween(duration))
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { expanded = !expanded }
    ) {
        if (expanded)
            Text(text = text, textAlign = textAlign)
        else
            Text(
                text = text,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
            )
    }
}

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    maxLines: Int = 3,
    duration: Int = ANIMATIONS_DURATION,
    textAlign: TextAlign = TextAlign.Center
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier
        .animateContentSize(tween(duration))
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { expanded = !expanded }
    ) {
        if (expanded)
            Text(text = text, textAlign = textAlign)
        else
            Text(
                text = text,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
            )
    }
}


@Preview
@Composable
private fun ExpandableTextPreview() {
    PokedexTheme {
        Surface {
            ExpandableText(maxLines = 3, text = LoremIpsum(60).values.joinToString(" "))
        }
    }
}