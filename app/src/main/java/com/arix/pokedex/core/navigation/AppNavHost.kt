package com.arix.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arix.pokedex.extensions.getTypeOf
import com.arix.pokedex.features.type_effectiveness.presentation.TypeEffectivenessScreen
import com.arix.pokedex.features.items.presentation.ui.ItemsScreen
import com.arix.pokedex.features.locations.presentation.ui.LocationDetailsScreen
import com.arix.pokedex.features.locations.presentation.ui.LocationsScreen
import com.arix.pokedex.features.move_details.presentation.ui.screens.LearnedByPokemonFullListScreen
import com.arix.pokedex.features.move_details.presentation.ui.screens.MoveDetailsScreen
import com.arix.pokedex.features.moves.presentation.ui.MovesScreen
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
            MovesScreen()
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
                    LearnedByPokemonFullListScreen(Gson().fromJson(it, getTypeOf<List<String>>()))
                }
            }
        }

        composable(Screen.ItemsScreen.route) {
            ItemsScreen()
        }

        composable(Screen.LocationsScreen.route) {
            LocationsScreen()
        }

        with(Screen.LocationsDetailsScreen) {
            composable(route) { backStackEntry ->
                backStackEntry.arguments?.getString(argumentKeys[0])?.let {
                    LocationDetailsScreen(it.toInt())
                }
            }
        }

        composable(Screen.AbilitiesScreen.route) {
            TypeEffectivenessScreen()
        }
    }
}