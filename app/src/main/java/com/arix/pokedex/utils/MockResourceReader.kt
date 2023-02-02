package com.arix.pokedex.utils

import android.content.Context
import com.arix.pokedex.R
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.TypeX
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

    fun getPokemonEvolutionChainMock(): PokemonEvolutionChain {
        val pokemonDetailsJson = context.resources.openRawResource(R.raw.pokemon_evolution_chain)
            .reader()
            .readText()

        return Gson().fromJson(pokemonDetailsJson, PokemonEvolutionChain::class.java)
            ?: throw Exception("Exception in preview")
    }

    fun getPokemonMoveMock(): RawMove {
        val pokemonMoveJson = context.resources.openRawResource(R.raw.pokemon_move)
            .reader()
            .readText()

        return Gson().fromJson(pokemonMoveJson, RawMove::class.java)
            ?: throw Exception("Exception in preview")
    }

    fun getPokemonMoveListMock(): List<RawMove> {
        val move = getPokemonMoveMock()
        val moveWater = move.copy(type = TypeX("water"), name = "sword-dance")
        val moveSteel = move.copy(type = TypeX("steel"), name = "thunder-punch")
        return listOf(
            move, moveWater, move, moveSteel, moveSteel, moveWater, move,
            move, moveWater, move, moveSteel, moveSteel, moveWater, move
        )
    }
}