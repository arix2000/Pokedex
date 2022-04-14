package com.arix.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.features.poke_list.presentation.pokemon_list.PokemonListScreen
import com.arix.pokedex.utils.Screen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PokeListScreen.route) {
        composable(Screen.PokeListScreen.route) { PokemonListScreen(navController) }
    }
}