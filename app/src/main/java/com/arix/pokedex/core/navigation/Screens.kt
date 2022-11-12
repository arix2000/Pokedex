package com.arix.pokedex.core.navigation

import com.arix.pokedex.extensions.putArgument
import com.arix.pokedex.extensions.withArgument

sealed class Screen(private var _route: String, val argumentKey: String = "") {
    val route: String
        get() {
            if (argumentKey.isNotEmpty())
                return _route.withArgument(argumentKey)
            return _route
        }

    object PokemonListScreen : Screen("pokemonListScreen")

    object MovesScreen : Screen("movesScreen")

    object MoveDetailsScreen : Screen("movesDetailsScreen", "moveId")

    object ItemsScreen : Screen("itemsScreen")

    object LocationsScreen : Screen("locationsScreen")

    object AbilitiesScreen : Screen("abilitiesScreen")

    object PokemonDetailsScreen
        : Screen("pokemonDetailsScreen", "pokemonName")
}