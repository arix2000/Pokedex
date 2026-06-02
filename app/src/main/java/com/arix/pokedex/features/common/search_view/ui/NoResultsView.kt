package com.arix.pokedex.features.common.search_view.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.arix.pokedex.R
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun NoResultsView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_results_anim))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp, horizontal = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            LottieAnimation(
                composition = composition,
                modifier = Modifier.height(220.dp),
                iterations = 1
            )
            Text(text = "No results found", fontSize = FontSizes.larger)
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Hmmm.. that SUSpicous, we tried so hard, but we couldn't find anything like that... Maybe try changing or refining your search.",
                fontSize = FontSizes.medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun NoResultsViewPreview() {
    PokedexTheme {
        Surface {
            NoResultsView()
        }
    }
}