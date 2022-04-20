package com.arix.pokedex.features.poke_list.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.features.poke_list.domain.model.details.Type
import com.arix.pokedex.features.poke_list.domain.model.details.TypeX
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.TextSize

@Composable
fun TypesSection(types: List<Type>, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.Center) {
        types.forEach {
            TypeItem(it)
            Spacer(modifier = modifier.width(2.dp))
        }
    }
}

@Composable
private fun TypeItem(type: Type) {
    Box(
        modifier = Modifier
            .background(type.getTypeColor(), CircleShape)
            .padding(vertical = 2.dp)
            .width(70.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = type.type.name, fontSize = TextSize.small)
    }
}

@Preview
@Composable
fun TypesSectionPreview() {
    PokedexTheme {
        Surface {
            TypesSection(
                types = listOf(
                    Type(1, TypeX("fighting", "")),
                    Type(2, TypeX("electric", ""))
                )
            )
        }
    }
}