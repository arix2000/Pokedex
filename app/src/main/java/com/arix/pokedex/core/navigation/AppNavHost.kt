package com.arix.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.features.poke_list.presentation.ui.PokemonListScreen
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PokemonListScreen.route) {
        composable(Screen.PokemonListScreen.route) { PokemonListScreen(navController) }

        with(Screen.PokemonDetailsScreen) {
            composable(route) { backStackEntry ->
                backStackEntry.arguments?.getString(argumentKey)?.let {
                    PokemonDetailsScreen(it)
                }
            }
        }
    }
}