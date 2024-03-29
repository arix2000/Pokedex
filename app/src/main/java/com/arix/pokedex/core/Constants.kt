package com.arix.pokedex.core

import androidx.compose.ui.unit.dp

object Constants {

    object Network {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    object UnitsOfMeasure {
        /**Kilograms**/
        const val KG = "kg"

        /**Meters**/
        const val M = "m"
    }

    object PokemonListScreen {
        const val POKEMON_LIST_ITEM_LIMIT = 30
    }

    object Language {
        const val ENGLISH_LANGUAGE_CODE = "en"
    }

    object PokemonDetailsScreen {
        const val NO_DESCRIPTION = "No description"
        const val ITEM_URL_PREFIX =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/"
        const val PNG_EXT = ".png"
        const val VARIETIES_SCROLL_HINT_ANIM_DURATION = 600
    }

    object FlavorTextEntriesVersion {
        const val POKEMON_DESCRIPTION_VERSION = "omega-ruby"
        const val MOVE_DESCRIPTION_VERSION = "omega-ruby-alpha-sapphire"
    }

    object AnimatedSection {
        const val ANIMATIONS_DURATION = 300
        const val FADE_OUT_DURATION = 180
    }

    object PokemonGenderConst {
        const val GANDER_RATE_WHEN_ONLY_MALES = 0
        const val GANDER_RATE_WHEN_ONLY_FEMALES = 8
    }

    object SplashScreen {
        const val MAX_LIMIT = 1000000
    }

    object SearchableLazyColumn {
        const val INITIAL_OFFSET = 0
    }

    object MoveScreen {
        const val EFFECT_CHANCE_ARG = "\$effect_chance"
        val TEXT_TILES_HEIGHT = 80.dp
        const val LEARNED_BY_POKEMON_LIST_MAX_SIZE = 6
        const val MOVES_ITEM_LIMIT = 30
    }

    object ItemsScreenConst {
        const val ITEMS_LIMIT = 30
        const val NO_EFFECT_STRING = "No effect."
    }

}