package com.arix.pokedex.core.navigation

import com.arix.pokedex.extensions.withArgument

sealed class Screen(private var _route: String, val argumentKey: String = "") {
    val route: String
        get() {
            if (argumentKey.isNotEmpty())
                return _route.withArgument(argumentKey)
            return _route
        }

    object PokemonListScreen : Screen("pokemon_list_screen")

    object PokemonDetailsScreen
        : Screen("pokemonDetailsScreen", "pokemonName") {

    }
}