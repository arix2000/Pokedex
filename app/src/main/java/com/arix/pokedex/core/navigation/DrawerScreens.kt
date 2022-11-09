package com.arix.pokedex.core.navigation

import com.arix.pokedex.R

enum class DrawerScreens(val screen: Screen, val drawerSpecs: DrawerSpecs) {
    POKEMON_LIST(Screen.PokemonListScreen, DrawerSpecs("Pokemon List", R.drawable.ic_pokemon_list)),
    MOVES(Screen.MovesScreen, DrawerSpecs("Moves", R.drawable.ic_moves)),
    ITEMS(Screen.ItemsScreen, DrawerSpecs("Items", R.drawable.ic_items)),
    LOCATIONS(Screen.LocationsScreen, DrawerSpecs("Locations", R.drawable.ic_locations)),
    ABILITIES(Screen.AbilitiesScreen, DrawerSpecs("Abilities", R.drawable.ic_abilities))
}