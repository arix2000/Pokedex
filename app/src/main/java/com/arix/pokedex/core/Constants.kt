package com.arix.pokedex.core

import androidx.compose.ui.unit.dp
import com.arix.pokedex.BuildConfig

object Constants {

    object Network {
        const val POKE_API_BASE_URL = "https://pokeapi.co/api/v2/"
        const val POKE_LIST_API_BASE_URL = BuildConfig.API_URL
        const val API_KEY_HEADER = "X-Api-Key"
        const val API_KEY = BuildConfig.API_KEY
        const val TIMEOUT_SECONDS = 30L
    }

    object UnitsOfMeasure {
        /**Kilograms**/
        const val KG = "kg"

        /**Meters**/
        const val M = "m"
    }

    object PokemonListScreen {
        const val POKEMON_LIST_ITEM_LIMIT = 30
        const val SINGLE_PAGE_BY_NAMES_LIMIT = 50
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

    object SearchableLazyColumn {
        const val INITIAL_OFFSET = 0
    }

    object MoveScreen {
        const val EFFECT_CHANCE_ARG = "\$effect_chance"
        val TEXT_TILES_HEIGHT = 80.dp
        const val LEARNED_BY_POKEMON_LIST_MAX_SIZE = 6
        const val ROW_ITEM_LIMIT = 30
    }

    object ItemsScreenConst {
        const val ITEMS_LIMIT = 30
        const val NO_EFFECT_STRING = "No effect."
    }

    object LocationsScreenConst {
        const val AREA_LIMIT = 4
        const val NO_REGION_STRING = "No region"
        const val SINGLE_AREA_SUFFIX = "area"
    }
}