package com.arix.pokedex.features.type_effectiveness.presentation.ui.components

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.features.type_effectiveness.presentation.mockTypeEffectivenessList
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun SelectableTypesList(types: List<SelectableType>) {

}

@Preview
@Composable
private fun SelectableTypesPreview() {
    PokedexTheme {
        Surface {
            SelectableTypesList(mockTypeEffectivenessList.map { it.type })
        }
    }
}