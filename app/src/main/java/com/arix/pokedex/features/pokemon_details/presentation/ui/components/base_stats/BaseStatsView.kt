package com.arix.pokedex.features.pokemon_details.presentation.ui.components.base_stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.features.pokemon_list.domain.model.details.Stat
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader

@Composable
fun BaseStatsView(stats: List<Stat>) {
    val textMeasurer = rememberTextMeasurer()
    val textStyle = LocalTextStyle.current
    val density = LocalDensity.current
    val allNames = stats.map { getFormattedStatText(it.name) }

    val maxTextWidth = remember(stats, textStyle, density) {
        val maxWidthPx = allNames.maxOfOrNull {
            textMeasurer.measure(it, textStyle).size.width
        } ?: 0
        with(density) { maxWidthPx.toDp() }
    }


    Column {
        for (stat in stats) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(getFormattedStatText(stat.name), Modifier.width(maxTextWidth))
                LinearProgressIndicator(
                    progress = stat.base_stat / 255f,
                    color = stat.color,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .clip(CircleShape)
                        .weight(1f)
                )
                Text(stat.base_stat.toString(), Modifier.width(32.dp), textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
private fun getFormattedStatText(name: String): String {
    return when (name.lowercase()) {
        "hp" -> stringResource(R.string.stat_hp)
        "attack" -> stringResource(R.string.stat_attack)
        "defense" -> stringResource(R.string.stat_defense)
        "special-attack" -> stringResource(R.string.stat_sp_attack)
        "special-defense" -> stringResource(R.string.stat_sp_defense)
        "speed" -> stringResource(R.string.stat_speed)
        else -> ""
    }
}

@Preview
@Composable
private fun BaseStatsViewPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }

    PokedexTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            BaseStatsView(pokemonDetails.stats)
        }
    }
}