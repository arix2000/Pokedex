package com.arix.pokedex.core.navigation

import androidx.navigation.NavHostController
import com.arix.pokedex.extensions.putArgument
import com.arix.pokedex.features.limited_list.domain.LimitedListType
import com.google.gson.Gson

class Navigator {
    private lateinit var navController: NavHostController

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    fun goToPokemonDetails(nameOrId: String) {
        with(Screen.PokemonDetailsScreen) {
            navController.navigate(route.putArgument(argumentKeys[0], nameOrId))
        }
    }

    fun goToLimitedList(names: List<String>, title: String, type: LimitedListType) {
        with(Screen.LimitedListScreen) {
            navController.navigate(
                route.putArgument(argumentKeys[0], Gson().toJson(names))
                    .putArgument(argumentKeys[1], title)
                    .putArgument(argumentKeys[2], type.name)
            )
        }
    }

    fun goToMoveDetails(moveId: String) {
        with(Screen.MoveDetailsScreen) {
            navController.navigate(route.putArgument(argumentKeys[0], moveId))
        }
    }

    fun goToLocationDetails(locationId: String) {
        with(Screen.LocationsDetailsScreen) {
            navController.navigate(route.putArgument(argumentKeys[0], locationId))
        }
    }
}