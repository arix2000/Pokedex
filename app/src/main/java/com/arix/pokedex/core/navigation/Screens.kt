package com.arix.pokedex.utils

sealed class Screen(val route: String) {
    object PokeListScreen: Screen("poke_list_screen")
}