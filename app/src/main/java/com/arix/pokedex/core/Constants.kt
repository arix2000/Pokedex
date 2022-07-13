package com.arix.pokedex.core

object Constants {

    object Network {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
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
        const val EVOLUTION_TO_PARAM_NAME = "evolves_to"
        const val MAX_POKEMON_EVOLUTIONS = 3
        const val ITEM_URL_PREFIX = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/"
        const val PNG_EXT = ".png"
    }

    object AnimatedSection {
        const val ANIMATIONS_DURATION = 300
        const val FADE_OUT_DURATION = 180
    }
}