package com.arix.pokedex.utils

import android.content.Context
import com.arix.pokedex.R
import com.arix.pokedex.features.items.domain.model.move_details.ItemDetails
import com.arix.pokedex.features.items.domain.model.move_details.raw.RawItemDetails
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.theme.ItemCategoryColors
import com.google.gson.Gson

/** Use for Preview only **/
class MockResourceReader(private val context: Context) {

    fun getPokemonDetailsMock(): PokemonDetails {
        val pokemonDetailsJson = context.resources.openRawResource(R.raw.pokemon_details_example)
            .reader()
            .readText()

        val rawPokeDetails = Gson().fromJson(pokemonDetailsJson, RawPokemonDetails::class.java)

        return PokemonDetails.fromRaw(rawPokeDetails)
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

    fun getPokemonMoveMock(): UiMove {
        val pokemonMoveJson = context.resources.openRawResource(R.raw.pokemon_move)
            .reader()
            .readText()

        return UiMove.fromRaw(Gson().fromJson(pokemonMoveJson, RawMove::class.java)
            ?: throw Exception("Exception in preview"))
    }

    fun getPokemonMoveListMock(): List<UiMove> {
        val move = getPokemonMoveMock()
        val moveWater = move.copy(type = Type("water"), name = "sword-dance")
        val moveSteel = move.copy(type = Type("steel"), name = "thunder-punch")
        return listOf(
            move, moveWater, move, moveSteel, moveSteel, moveWater, move,
            move, moveWater, move, moveSteel, moveSteel, moveWater, move
        )
    }

    fun getPokemonItemMock(): ItemDetails {
        val pokemonMoveJson = context.resources.openRawResource(R.raw.pokemon_item)
            .reader()
            .readText()

        return ItemDetails.fromRaw(Gson().fromJson(pokemonMoveJson, RawItemDetails::class.java)
            ?: throw Exception("Exception in preview"))
    }

    fun getPokemonItemListMock(): List<ItemDetails> {
        val item = getPokemonItemMock()
        val item2 = getPokemonItemMock().copy(name = "ultra-ball", categoryColor = ItemCategoryColors.pokeBalls)
        val item3 = getPokemonItemMock().copy(name = "x-attack", categoryColor = ItemCategoryColors.battle)
        val item4 = getPokemonItemMock().copy(name = "flame-mail", categoryColor = ItemCategoryColors.mail)
        val item5 = getPokemonItemMock().copy(name = "tm56", categoryColor = ItemCategoryColors.allMachines)
        return listOf(
            item3, item2, item, item3, item5, item4, item5, item3, item,
            item, item5, item2, item, item2, item5, item4, item, item4
        )
    }
}