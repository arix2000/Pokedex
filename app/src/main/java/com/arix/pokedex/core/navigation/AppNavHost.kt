package com.arix.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arix.pokedex.extensions.putArgument
import com.arix.pokedex.features.poke_list.presentation.ui.PokemonListScreen
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.PokemonListScreen.route) {
        composable(Screen.PokemonListScreen.route) {
            PokemonListScreen {
                with(Screen.PokemonDetailsScreen) {
                    navController.navigate(route.putArgument(argumentKey, it))
                }
            }
        }

        with(Screen.PokemonDetailsScreen) {
            composable(route) { backStackEntry ->
                backStackEntry.arguments?.getString(argumentKey)?.let {
                    PokemonDetailsScreen(it)
                }
            }
        }
    }
}