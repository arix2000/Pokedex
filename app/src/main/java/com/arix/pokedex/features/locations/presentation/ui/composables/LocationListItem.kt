package com.arix.pokedex.features.locations.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.extensions.clickableOnceInTime
import com.arix.pokedex.extensions.toSentenceCase
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.domain.model.list.LocationRegion
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes

@Composable
fun LocationListItem(location: LocationItem, onClick: (id: Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(Shapes.large)
            .clickableOnceInTime { onClick(location.id) }
            .background(getBrushBasedBy(location.region))
            .padding(vertical = 14.dp, horizontal = 20.dp)
    ) {
        Text(
            text = location.name.toSentenceCase(),
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = FontSizes.large,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Card(
            shape = Shapes.large,
            backgroundColor = location.region?.getRegionColor() ?: Color.Black,
        ) {
            Box(
                Modifier.padding(top = 2.dp, bottom = 2.dp, start = 4.dp, end = 7.dp)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = location.region?.name?.ifBlank {
                            "No region"
                        }?.capitalize(Locale.current) ?: "No region",
                        fontSize = FontSizes.normal,
                    )
                }
            }
        }
    }
}

private fun getBrushBasedBy(region: LocationRegion?): Brush {
    return Brush.horizontalGradient(
        colors = listOf(
            Color.Black,
            region?.getRegionColor()?.copy(0.3f) ?: Color.Black,
        )
    )
}

@Preview
@Composable
private fun LocationListItemPreview() {
    PokedexTheme {
        Surface {
            LocationListItem(
                location = LocationItem(
                    1,
                    "Location name",
                    region = LocationRegion("Kanto", "")
                )
            ) { }
        }
    }
}