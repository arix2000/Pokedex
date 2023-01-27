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
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.core.navigation.AppNavHost
import com.arix.pokedex.core.navigation.DrawerScreens
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.core.navigation.Screen
import com.arix.pokedex.features.common.AppTopBar
import com.arix.pokedex.features.common.drawer.NavDrawerContent
import com.arix.pokedex.theme.BlackA70
import com.arix.pokedex.theme.BlackLight
import com.arix.pokedex.theme.PokedexTheme
import org.koin.androidx.compose.get

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
    var enableDrawer by remember { mutableStateOf(true) }
    var topBarTitle: String? by remember { mutableStateOf(null) }
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController().apply {
        get<Navigator>().setNavController(this)
        addOnDestinationChangedListener { _, _, _ ->
            showBackButton = previousBackStackEntry != null
            enableDrawer =
                DrawerScreens.values().any { it.screen.route == currentDestination?.route }
            topBarTitle = if (currentDestination?.route == Screen.LearnedByPokemonFullList.route)
                Screen.LearnedByPokemonFullList.getTopBarTitle(
                    currentBackStackEntry?.arguments?.getString(Screen.LearnedByPokemonFullList.argumentKeys[1])
                )
            else null
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppTopBar(navController, showBackButton, scaffoldState, topBarTitle) },
            drawerContent = { NavDrawerContent(navController, scaffoldState) },
            drawerScrimColor = BlackA70,
            drawerBackgroundColor = BlackLight,
            drawerShape = RectangleShape,
            drawerGesturesEnabled = enableDrawer
        ) {
            AppNavHost(navController)
        }
    }
}