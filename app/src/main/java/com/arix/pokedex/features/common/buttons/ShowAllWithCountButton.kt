package com.arix.pokedex.features.common.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.Shapes


@Composable
fun ShowAllWithCountButton(count: Int, onClicked: () -> Unit) {
    Button(
        onClick = { onClicked(); },
        contentPadding = PaddingValues(
            top = 3.dp, bottom = 3.dp, start = 10.dp, end = 5.dp
        ),
        shape = Shapes.large,
        modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
    ) {
        Text(
            text = stringResource(R.string.show_all, count).uppercase(),
            fontSize = FontSizes.minimum
        )
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.White
        )
    }
}