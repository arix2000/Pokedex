package com.arix.pokedex.core

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
        const val POKEMON_SEARCH_LIST_ITEM_LIMIT = 20
        const val POKEMON_LIST_INITIAL_OFFSET = 0
    }

    object Language {
        const val ENGLISH_LANGUAGE_CODE = "en"
    }

    object PokemonDetailsScreen {
        const val DESIRED_DESCRIPTION_VERSION = "omega-ruby"
        const val NO_DESCRIPTION = "No description"
        const val ITEM_URL_PREFIX = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/"
        const val PNG_EXT = ".png"
        const val VARIETIES_SCROLL_HINT_ANIM_DURATION = 600
    }

    object AnimatedSection {
        const val ANIMATIONS_DURATION = 300
        const val FADE_OUT_DURATION = 180
    }

    object PokemonGenderConst {
        const val GANDER_RATE_WHEN_ONLY_MALES = 0
        const val GANDER_RATE_WHEN_ONLY_FEMALES = 8
    }

    object PokemonMovesScreen {
        const val MOVES_INITIAL_OFFSET = 0
        const val MOVES_ITEM_LIMIT = 30
    }

    object SplashScreen {
        const val MAX_LIMIT = 1000000
    }
}