package com.arix.pokedex.core.navigation

import androidx.navigation.NavHostController
import com.arix.pokedex.extensions.putArgument
import com.google.gson.Gson

class Navigator {
    private lateinit var navController: NavHostController

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    fun goToImageFullScreen() {
        navController.navigate(Screen.ImageFullScreen.route)
    }

    fun goToPokemonDetails(nameOrId: String) {
        with(Screen.PokemonDetailsScreen) {
            navController.navigate(route.putArgument(argumentKeys[0], nameOrId))
        }
    }

    fun goToLearnedByPokemonList(pokemonNames: List<String>, moveName: String) {
        with(Screen.LearnedByPokemonFullList) {
            navController.navigate(
                route.putArgument(argumentKeys[0], Gson().toJson(pokemonNames))
                    .putArgument(argumentKeys[1], moveName)
            )
        }
    }
}