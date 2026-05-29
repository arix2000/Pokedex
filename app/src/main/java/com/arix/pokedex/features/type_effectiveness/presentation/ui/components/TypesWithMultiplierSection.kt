package com.arix.pokedex.features.type_effectiveness.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.presentation.mockTypeEffectivenessList
import com.arix.pokedex.theme.AppShapes
import com.arix.pokedex.theme.BlackLight
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.FadingHorizontalDivider

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TypesWithMultiplierSection(title: String, typesToMultiplier: Map<Type, Double>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackLight, shape = AppShapes.large)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = title, fontSize = FontSizes.large, fontWeight = FontWeight.Bold)
        FadingHorizontalDivider()
        FlowRow {
            typesToMultiplier.forEach {
                TypeWithMultiplierItem(
                    it.toPair(),
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun TypesWithMultipliersSectionPreview() {
    PokedexTheme {
        Surface {
            Box(modifier = Modifier.padding(12.dp)) {
                TypesWithMultiplierSection(
                    stringResource(R.string.damage_multiplier_normal_label),
                    mockTypeEffectivenessList.first().typesToMultipliers.mapValues { it.value.baseMultiplier })
            }
        }
    }
}