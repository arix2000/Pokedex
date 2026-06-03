package com.arix.pokedex.utils

import android.content.Context
import com.arix.pokedex.R
import com.arix.pokedex.features.items.domain.model.item_details.ItemDetails
import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails
import com.arix.pokedex.features.locations.domain.model.details.LocationDetails
import com.arix.pokedex.features.locations.domain.model.details.UiLocationArea
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.domain.model.list.LocationRegion
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.details.Ability
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.theme.ItemCategoryColors
import com.google.gson.Gson
import org.jetbrains.annotations.TestOnly

@TestOnly
class MockResourceReader(private val context: Context) {

    fun getPokemonDetailsMock(): PokemonDetails {
        val pokemonDetailsJson = context.resources.openRawResource(R.raw.pokemon_details_example)
            .reader()
            .readText()

        val rawPokeDetails = Gson().fromJson(pokemonDetailsJson, RawPokemonDetails::class.java)

        return PokemonDetails.fromRaw(
            rawPokeDetails,
            getAbilitiesMock(),
            getLocationItemsMock(),
            getPokemonMoveListMock()
        )
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

        return UiMove.fromRaw(
            Gson().fromJson(pokemonMoveJson, RawMove::class.java)
                ?: throw Exception("Exception in preview")
        )
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

        return ItemDetails.fromRaw(
            Gson().fromJson(pokemonMoveJson, RawItemDetails::class.java)
                ?: throw Exception("Exception in preview")
        )
    }

    fun getPokemonItemListMock(): List<ItemDetails> {
        val item = getPokemonItemMock()
        val item2 = getPokemonItemMock().copy(
            name = "ultra-ball",
            categoryColor = ItemCategoryColors.pokeBalls
        )
        val item3 =
            getPokemonItemMock().copy(name = "x-attack", categoryColor = ItemCategoryColors.battle)
        val item4 =
            getPokemonItemMock().copy(name = "flame-mail", categoryColor = ItemCategoryColors.mail)
        val item5 =
            getPokemonItemMock().copy(name = "tm56", categoryColor = ItemCategoryColors.allMachines)
        return listOf(
            item3, item2, item, item3, item5, item4, item5, item3, item,
            item, item5, item2, item, item2, item5, item4, item, item4
        )
    }

    fun getLocationDetailsMock(): LocationDetails {
        val locationDetailsJson = context.resources.openRawResource(R.raw.location_details_example)
            .reader()
            .readText()

        return Gson().fromJson(locationDetailsJson, LocationDetails::class.java)
            ?: throw Exception("Exception in preview")
    }

    fun getLocationAreasMock(): List<UiLocationArea> {
        val locationAreasJson = context.resources.openRawResource(R.raw.location_areas_example)
            .reader()
            .readText()

        return Gson().fromJson(locationAreasJson, Array<UiLocationArea>::class.java).toList()
    }

    fun getAbilitiesMock(): List<Ability> {
        return listOf(
            Ability(
                name = "Overgrow",
                url = "https://pokeapi.co/api/v2/ability/65/",
                isHidden = false,
                description = "Powers up Grass-type moves when the Pokémon's HP is low."
            ),
            Ability(
                name = "Chlorophyll",
                url = "https://pokeapi.co/api/v2/ability/34/",
                isHidden = true,
                description = "Boosts the Pokémon's Speed stat in harsh sunlight."
            ),
            Ability(
                name = "As One",
                url = "https://pokeapi.co/api/v2/ability/266/",
                isHidden = false,
                description = "This Ability combines the effects of both Calyrex's Unnerve Ability and Glastrier's Chilling Neigh Ability. It prevents opposing Pokémon from consuming held Berries due to psychological pressure, while simultaneously increasing the user's Attack stat by one stage whenever it knocks out a target on the battlefield, making it incredibly dangerous in prolonged engagements."
            )
        )
    }

    fun getLocationItemsMock(): List<LocationItem> {
        return listOf(
            LocationItem(
                id = 1,
                name = "Pallet Town",
                region = LocationRegion("kanto", "https://pokeapi.co/api/v2/region/1/")
            ),
            LocationItem(
                id = 2,
                name = "New Bark Town",
                region = LocationRegion("johto", "https://pokeapi.co/api/v2/region/2/")
            ),
            LocationItem(
                id = 3,
                name = "Littleroot Town",
                region = LocationRegion("hoenn", "https://pokeapi.co/api/v2/region/3/")
            ),
            LocationItem(
                id = 4,
                name = "Twinleaf Town",
                region = LocationRegion("sinnoh", "https://pokeapi.co/api/v2/region/4/")
            ),
            LocationItem(
                id = 5,
                name = "Nuvema Town",
                region = LocationRegion("unova", "https://pokeapi.co/api/v2/region/5/")
            ),
            LocationItem(
                id = 6,
                name = "Vaniville Town",
                region = LocationRegion("kalos", "https://pokeapi.co/api/v2/region/6/")
            ),
            LocationItem(
                id = 7,
                name = "Iki Town",
                region = LocationRegion("alola", "https://pokeapi.co/api/v2/region/7/")
            ),
            LocationItem(
                id = 8,
                name = "Postwick",
                region = LocationRegion("galar", "https://pokeapi.co/api/v2/region/8/")
            ),
            LocationItem(
                id = 9,
                name = "Jubilife Village",
                region = LocationRegion("hisui", "https://pokeapi.co/api/v2/region/9/")
            ),
            LocationItem(
                id = 10,
                name = "Cabo Poco",
                region = LocationRegion("paldea", "https://pokeapi.co/api/v2/region/10/")
            ),
            LocationItem(
                id = 11,
                name = "Unknown Island",
                region = null
            )
        )
    }
}