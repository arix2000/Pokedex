package com.arix.pokedex.features.type_effectiveness.presentation.ui.components

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.presentation.mockTypeEffectivenessList
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun TypesWithMultipliersSection(title: String, multipliersToTypes: Map<Type, Double>) {

}

@Preview
@Composable
private fun TypesWithMultipliersSectionPreview() {
    PokedexTheme {
        Surface {
            TypesWithMultipliersSection(
                "Damaged normally by: ",
                mockTypeEffectivenessList.first().typesToMultipliers.mapValues { it.value.baseMultiplier })
        }
    }
}