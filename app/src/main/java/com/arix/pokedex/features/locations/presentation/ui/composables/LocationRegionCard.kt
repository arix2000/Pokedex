package com.arix.pokedex.features.locations.presentation.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.arix.pokedex.core.Constants.LocationsScreenConst.NO_REGION_STRING
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.Shapes

@Composable
fun LocationRegionCard(regionName: String?, regionColor: Color) {
    Card(
        shape = Shapes.large,
        backgroundColor = regionColor,
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
                    text = regionName?.ifBlank {
                        NO_REGION_STRING
                    }?.capitalize(Locale.current) ?: NO_REGION_STRING,
                    fontSize = FontSizes.normal,
                )
            }
        }
    }
}