package com.arix.pokedex.core.navigation

sealed class Screen(val route: String) {
    object PokeListScreen: Screen("poke_list_screen")
}