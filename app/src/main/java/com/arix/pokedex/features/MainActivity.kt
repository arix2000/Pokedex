package com.arix.pokedex.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.core.navigation.AppNavHost
import com.arix.pokedex.features.common.AppTopBar
import com.arix.pokedex.features.common.drawer.NavDrawerContent
import com.arix.pokedex.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                AppContainer()
            }
        }
    }
}

@Composable
fun AppContainer() {
    var showBackButton by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController().apply {
        addOnDestinationChangedListener { _, _, _ ->
            showBackButton = previousBackStackEntry != null
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppTopBar(navController, showBackButton, scaffoldState) },
            drawerContent = { NavDrawerContent(navController, scaffoldState) },
            drawerScrimColor = BlackA70,
            drawerBackgroundColor = BlackLight,
            drawerShape = RectangleShape
        ) {
            AppNavHost(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        AppContainer()
    }
}