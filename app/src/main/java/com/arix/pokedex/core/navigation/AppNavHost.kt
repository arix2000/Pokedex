package com.arix.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arix.pokedex.extensions.getTypeOf
import com.arix.pokedex.extensions.putArgument
import com.arix.pokedex.features.abilities.AbilitiesScreen
import com.arix.pokedex.features.items.ItemsScreen
import com.arix.pokedex.features.locations.LocationsScreen
import com.arix.pokedex.features.move_details.presentation.ui.screens.LearnedByPokemonFullList
import com.arix.pokedex.features.move_details.presentation.ui.screens.MoveDetailsScreen
import com.arix.pokedex.features.moves.presentation.ui.MovesScreen
import com.arix.pokedex.features.pokemon_details.presentation.ui.ImageFullScreen
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsScreen
import com.arix.pokedex.features.pokemon_list.presentation.ui.PokemonListScreen
import com.google.gson.Gson

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.PokemonListScreen.route) {
        composable(Screen.PokemonListScreen.route) {
            PokemonListScreen()
        }

        with(Screen.PokemonDetailsScreen) {
            composable(route) { backStackEntry ->
                backStackEntry.arguments?.getString(argumentKeys[0])?.let {
                    PokemonDetailsScreen(it)
                }
            }
        }

        composable(Screen.MovesScreen.route) {
            MovesScreen {
                with(Screen.MoveDetailsScreen) {
                    navController.navigate(route.putArgument(argumentKeys[0], it))
                }
            }
        }
        with(Screen.MoveDetailsScreen) {
            composable(route) { backStackEntry ->
                backStackEntry.arguments?.getString(argumentKeys[0])?.let {
                    MoveDetailsScreen(it.toInt())
                }
            }
        }

        with(Screen.LearnedByPokemonFullList) {
            composable(route) { backStackEntry ->
                backStackEntry.arguments?.getString(argumentKeys[0])?.let {
                    LearnedByPokemonFullList(Gson().fromJson(it, getTypeOf<List<String>>()))
                }
            }
        }

        composable(Screen.ItemsScreen.route) {
            ItemsScreen()
        }

        composable(Screen.LocationsScreen.route) {
            LocationsScreen()
        }

        composable(Screen.AbilitiesScreen.route) {
            AbilitiesScreen()
        }

        composable(Screen.ImageFullScreen.route) {
            ImageFullScreen()
        }
    }
}