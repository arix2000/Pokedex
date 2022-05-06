package com.arix.pokedex.utils

import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.arix.pokedex.R
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.google.gson.Gson

/** Use for Preview only **/
class MockResourceReader(private val context: Context) {

    fun getPokemonDetailsMock(): PokemonDetails {
        val pokemonDetailsJson = context.resources.openRawResource(R.raw.pokemon_details_example)
            .reader()
            .readText()

        return Gson().fromJson(pokemonDetailsJson, PokemonDetails::class.java)
            ?: PokemonDetails.EMPTY
    }

    fun getPokemonSpeciesMock(): PokemonSpecies {
        val pokemonDetailsJson = context.resources.openRawResource(R.raw.pokemon_spieces_example)
            .reader()
            .readText()

        return Gson().fromJson(pokemonDetailsJson, PokemonSpecies::class.java)
            ?: throw Exception("Exception in preview")
    }
}