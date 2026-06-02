package com.arix.pokedex.views

import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.arix.pokedex.R
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun DefaultProgressIndicator(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.progress_indicator))

    LottieAnimation(
        composition = composition,
        modifier = modifier.size(40.dp),
        iterations = LottieConstants.IterateForever
    )
}

@Preview
@Composable
private fun DefaultProgressIndicatorPreview() {
    PokedexTheme {
        Surface {
            DefaultProgressIndicator()
        }
    }
}