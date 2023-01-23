package com.arix.pokedex.features.pokemon_list.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.pokemon_list.domain.model.details.TypeX
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.FontSizes

@Composable
fun TypesSection(
    types: List<Type>,
    spacing: Dp,
    itemFontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.Center) {
        types.forEach {
            TypeItem(it.type, modifier, itemFontSize)
            Spacer(modifier = Modifier.width(spacing))
        }
    }
}

@Composable
fun TypeItem(type: TypeX, modifier: Modifier = Modifier, itemFontSize: TextUnit) {
    Box(
        modifier = modifier
            .background(type.getTypeColor(), CircleShape)
            .padding(vertical = 2.dp)
            .width(70.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = type.name, fontSize = itemFontSize)
    }
}

@Preview
@Composable
private fun TypesSectionPreview() {
    PokedexTheme {
        Surface {
            TypesSection(
                types = listOf(
                    Type(1, TypeX("fighting", "")),
                    Type(2, TypeX("electric", ""))
                ),
                spacing = 2.dp,
                itemFontSize = FontSizes.minimum
            )
        }
    }
}