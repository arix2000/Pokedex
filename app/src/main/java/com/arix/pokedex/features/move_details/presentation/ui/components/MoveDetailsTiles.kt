package com.arix.pokedex.features.move_details.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.core.Constants.MoveScreen.TEXT_TILES_HEIGHT
import com.arix.pokedex.extensions.hasOneItem
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import kotlin.math.abs

private val RowScope.tileModifier: Modifier
    get() = Modifier
        .weight(1f)
        .padding(5.dp)
        .height(TEXT_TILES_HEIGHT)

@Composable
fun MoveDetailsTiles(move: UiMove) {
    Column {
        Spacer(Modifier.height(10.dp))
        BaseMoveInfo(move)
        EffectMoveInfo(move)
        MoveMetaData(move)
        if (move.stat_changes.isNotEmpty())
            StatChanges(move)
    }
}

@Composable
private fun BaseMoveInfo(move: UiMove) {
    with(move) {
        Row {
            BorderedTextTile(
                label = stringResource(R.string.power_label),
                value = power.toString(),
                borderColor = typeColor,
                modifier = tileModifier
            )
            BorderedTextTile(
                label = stringResource(R.string.pp_label),
                value = pp.toString(),
                borderColor = typeColor,
                modifier = tileModifier,
            )
            BorderedTextTile(
                label = stringResource(R.string.accuraccy_label),
                value = accuracy?.toString() ?: stringResource(R.string.cannot_miss),
                borderColor = typeColor,
                modifier = tileModifier,
            )
        }
        Row {
            BorderedTextTile(
                label = "Target:",
                value = target,
                borderColor = typeColor,
                modifier = tileModifier
            )
            BorderedTextTile(
                label = "Category:",
                value = damageClass.name.lowercase().capitalize(Locale.current),
                borderColor = typeColor,
                modifier = tileModifier
            )
        }
        BorderedTextTile(
            text = description ?: stringResource(R.string.desc_not_found),
            borderColor = typeColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
}

@Composable
private fun EffectMoveInfo(move: UiMove) {
    val criticalRate: Double = remember {
        if (move.meta.crit_rate <= 4) (move.meta.crit_rate + 1) * 6.25 else 50
    } as Double
    Row {
        BorderedTextTile(
            label = stringResource(R.string.effect_chance_label),
            value = move.effectChance,
            borderColor = move.typeColor,
            modifier = tileModifier
        )
        BorderedTextTile(
            label = stringResource(R.string.critical_rate_label),
            value = "$criticalRate%",
            borderColor = move.typeColor,
            modifier = tileModifier
        )
        BorderedTextTile(
            label = stringResource(R.string.priority_label),
            value = move.priority.toString(),
            borderColor = move.typeColor,
            modifier = tileModifier
        )
    }
    BorderedTextTile(
        text = move.effectDesc ?: stringResource(R.string.effect_desc_not_found),
        borderColor = move.typeColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
}

@Composable
private fun MoveMetaData(move: UiMove) {
        with(move.meta) {
            Row {
                BorderedTextTile(
                    label = "Ailment:",
                    value = ailment.name,
                    borderColor = move.typeColor,
                    modifier = tileModifier,
                    enabled = ailment.name != "none"
                )
                BorderedTextTile(
                    label = "Ailment chance:",
                    value = "$ailment_chance%",
                    borderColor = move.typeColor,
                    modifier = tileModifier,
                    enabled = ailment_chance != 0
                )
            }
            Row {
                val duration =
                    if (turnsNotNull()) "${getTurnsRange()} turns" else "none"
                val hits =
                    if (hitsNotNull()) "${getHitsRange()} hits" else "1 hit"
                BorderedTextTile(
                    label = "Duration:",
                    value = duration,
                    borderColor = move.typeColor,
                    modifier = tileModifier,
                    enabled = turnsNotNull()
                )
                BorderedTextTile(
                    label = "Hits:",
                    value = hits,
                    borderColor = move.typeColor,
                    modifier = tileModifier,
                    enabled = hitsNotNull()
                )
            }
            Row {
                BorderedTextTile(
                    label = "Healing:",
                    value = "$healing%",
                    borderColor = move.typeColor,
                    modifier = tileModifier,
                    enabled = healing != 0
                )
                BorderedTextTile(
                    label = if (drain >= 0) "HP Drain:" else "Recoil:",
                    value = "${abs(drain)}%",
                    borderColor = move.typeColor,
                    modifier = tileModifier,
                    enabled = drain != 0
                )
                BorderedTextTile(
                    label = "Flinch chance:",
                    value = "$flinch_chance%",
                    borderColor = move.typeColor,
                    modifier = tileModifier,
                    enabled = flinch_chance != 0
                )
            }
        }
}

@Composable
private fun StatChanges(move: UiMove) {
    Row {
        with(move) {
            BorderedTextTile(
                label = "Stat chance:",
                value = "${meta.stat_chance}%",
                borderColor = typeColor,
                modifier = tileModifier,
                enabled = move.meta.stat_chance != 0
            )
            BorderedTextTile(
                borderColor = move.typeColor,
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp),
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Stat Changes:")
                GridView(
                    data = move.stat_changes,
                    cells = 2,
                    alwaysFillSpace = true,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = it.getStatChangeText(),
                        textAlign = if (move.stat_changes.hasOneItem()) TextAlign.Center else null,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MoveDetailsTilesPreview() {
    val context = LocalContext.current
    val move = remember { MockResourceReader(context).getPokemonMoveMock() }
    PokedexTheme {
        Surface {
            MoveDetailsTiles(move)
        }
    }
}