package com.arix.pokedex.features.type_effectiveness.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.features.type_effectiveness.presentation.mockTypeEffectivenessList
import com.arix.pokedex.theme.PokedexTheme
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectableTypesList(
    types: List<SelectableType>,
    onTypeClick: (SelectableType) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        types.forEach { type ->
            SelectableTypeItem(
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 1.dp),
                selectableType = type,
                onClick = { onTypeClick(type) })
        }
    }
}

@Preview
@Composable
private fun SelectableTypesPreview() {
    PokedexTheme {
        Surface(modifier = Modifier.width(250.dp)) {
            SelectableTypesList(
                mockTypeEffectivenessList.map { it.type.copy(isSelected = Random.nextBoolean()) },
                onTypeClick = {})
        }
    }
}