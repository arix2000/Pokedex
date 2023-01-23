package com.arix.pokedex.features.common.text

import androidx.compose.animation.core.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.theme.PokedexTheme
import kotlinx.coroutines.delay

@Composable
fun SlidingText(modifier: Modifier = Modifier, text: String, style: TextStyle = TextStyle()) {
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = true) {
        while (true) {
            scrollState.animateScrollTo(
                scrollState.maxValue,
                tween(2000, delayMillis = 2000, easing = LinearEasing)
            )
            delay(1000)
            scrollState.scrollTo(0)
        }
    }
    Text(
        text = text,
        style = style,
        modifier = Modifier
            .horizontalScroll(scrollState, false)
            .then(modifier),
        maxLines = 1
    )


}

@Preview
@Composable
private fun SlidingTextPreview() {
    PokedexTheme {
        Surface {
            SlidingText(text = "Some very very long long text")
        }
    }
}