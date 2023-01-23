package com.arix.pokedex.core.navigation

import com.arix.pokedex.extensions.withArgument

sealed class Screen(
    private var _route: String,
    vararg val argumentKeys: String = emptyArray(),
) {
    val route: String
        get() {
            var finalRoute = _route
            argumentKeys.forEach { argumentKey ->
                    finalRoute = finalRoute.withArgument(argumentKey)
            }
            return finalRoute
        }

    fun getTopBarTitle(data: String? = null): String? {
        return when(this) {
           LearnedByPokemonFullList -> "$data can be learned by:"
           else -> null
        }
    }

    object PokemonListScreen : Screen("pokemonListScreen")

    object MovesScreen : Screen("movesScreen")

    object MoveDetailsScreen : Screen("movesDetailsScreen", "moveId")

    object ItemsScreen : Screen("itemsScreen")

    object LocationsScreen : Screen("locationsScreen")

    object AbilitiesScreen : Screen("abilitiesScreen")

    object PokemonDetailsScreen
        : Screen("pokemonDetailsScreen", "pokemonName")

    object LearnedByPokemonFullList
        : Screen("learnedByPokemonFullList", "pokemonNames", "moveName")
}