package com.arix.pokedex.features.locations.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
fun LocationListItem(location: LocationItem, modifier: Modifier = Modifier, onClick: (id: Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
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
        LocationRegionCard(location.region?.name, location.region?.getRegionColor() ?: Color.Black)
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
                modifier = Modifier.padding(5.dp),
                location = LocationItem(
                    1,
                    "Location name",
                    region = LocationRegion("Kanto", "")
                )
            ) { }
        }
    }
}