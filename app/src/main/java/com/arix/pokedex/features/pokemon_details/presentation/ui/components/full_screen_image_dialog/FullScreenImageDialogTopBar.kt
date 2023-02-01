package com.arix.pokedex.features.pokemon_details.presentation.ui.components.full_screen_image_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.theme.BlackSoft
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun FullScreenDialogTopBar(modifier: Modifier = Modifier, onCancelClicked: () -> Unit) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = BlackSoft,
        contentPadding = PaddingValues(all = 0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = "Pokemon Logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(all = 4.dp)
                    .align(Alignment.Center)
            )

            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { onCancelClicked() }) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@Preview
@Composable
private fun FullScreenDialogTopBarPreview() {
    PokedexTheme {
        Surface {
            FullScreenDialogTopBar {}
        }
    }
}