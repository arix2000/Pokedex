package com.arix.pokedex.features.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.R
import com.arix.pokedex.theme.BlackSoft
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun AppTopBar(navController: NavController, showBackButton: Boolean) {
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
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 4.dp)
            ) {
                if (showBackButton)
                    BackIconButton(navController)
                else
                    MenuIconButton()
            }
        }
    }
}

@Composable
private fun BackIconButton(navController: NavController) {
    IconButton(onClick = { navController.navigateUp() }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back"
        )
    }
}

@Composable
private fun MenuIconButton() {
    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = "Menu"
        )
    }
}

@Preview
@Composable
private fun AppTopBarPreview() {
    PokedexTheme {
        Surface {
            AppTopBar(rememberNavController(), false)
        }
    }
}