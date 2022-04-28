package com.arix.pokedex.features.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
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
fun AppTopBar() {
    TopAppBar(backgroundColor = BlackSoft, contentPadding = PaddingValues(all = 0.dp)) {
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
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    PokedexTheme {
        Surface {
            AppTopBar()
        }
    }
}