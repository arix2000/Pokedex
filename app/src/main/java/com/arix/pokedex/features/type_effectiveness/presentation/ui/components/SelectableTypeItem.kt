package com.arix.pokedex.features.type_effectiveness.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.arix.pokedex.features.common.boxes.OffsetCheckIndicator
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.theme.LightGray
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun SelectableTypeItem(
    selectableType: SelectableType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = LightGray
    val borderWidth = 2.dp
    Box(modifier = modifier.clickable { onClick() }) {
        Box(
            modifier = modifier
                .border(
                    borderWidth,
                    if (selectableType.isSelected) borderColor else Color.Transparent,
                    CircleShape
                )
                .background(selectableType.getTypeColor(), CircleShape)
                .padding(vertical = 6.dp, horizontal = 26.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = selectableType.name, softWrap = false, lineHeight = 0.6.em)
        }
        if (selectableType.isSelected)
            OffsetCheckIndicator(borderWidth, borderColor)
    }
}

@Preview
@Composable
private fun SelectableTypeItemPreview() {
    PokedexTheme {
        Surface {
            Box(Modifier.padding(16.dp)) {
                SelectableTypeItem(
                    selectableType = SelectableType(
                        isSelected = true,
                        type = Type("psychic")
                    ),
                    {}
                )
            }
        }
    }
}