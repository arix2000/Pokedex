package com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.theme.BlackLight
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.theme.FontSizes

@Composable
fun ExpandableSection(
    title: String,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    var expanded by remember { mutableStateOf(expandedInitially) }
    Column(
        Modifier
            .padding(horizontal = 3.dp)
            .background(color = BlackLight, shape = Shapes.large)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .clickable { expanded = !expanded }
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Text(text = title, fontSize = FontSizes.large)
            RotatingIcon(rotated = expanded, title)
        }
        ExpandableBox(
            expanded,
            content,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        )
    }
}

@Preview
@Composable
private fun ExpandableSectionPreview() {
    PokedexTheme {
        Surface {
            ExpandableSection(title = "Description") {
                Text(text = "SOME TEXT")
                Image(
                    painter = painterResource(id = R.drawable.scyther),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

