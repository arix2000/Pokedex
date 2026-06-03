package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.extensions.ifAllSuccess
import com.arix.pokedex.features.locations.data.LocationRepository
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.features.pokemon_list.domain.model.RawPokemonEncounters
import com.arix.pokedex.features.pokemon_list.domain.model.details.Ability
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.MoveWrapper
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawAbility
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetPokemonUseCase(
    private val repository: PokemonRepository,
    private val locationRepository: LocationRepository,
    private val movesRepository: MovesRepository
) {
    suspend operator fun invoke(name: String): ApiResponse<PokemonDetails> {
        val locations = fetchAreaLocationEncounters(name)
        if (locations.isError()) return ApiResponse.Error(locations.message!!)

        return when (val pokemonResult = repository.getPokemon(name)) {
            is ApiResponse.Error -> ApiResponse.Error(pokemonResult.message!!)
            is ApiResponse.Success -> {
                val rawPokemonDetails = pokemonResult.data!!

                val moves = fetchMoves(rawPokemonDetails.moves)
                if (moves.isError()) {
                    return ApiResponse.Error(moves.message!!)
                }

                val abilities = fetchAbilities(rawPokemonDetails.abilities)
                if (abilities.isError()) {
                    return ApiResponse.Error(abilities.message!!)
                }
                return ApiResponse.Success(
                    PokemonDetails.fromRaw(
                        rawPokemonDetails, abilities.data!!, locations.data!!, moves.data!!
                    )
                )
            }
        }
    }

    suspend fun fetchAreaLocationEncounters(name: String): ApiResponse<List<LocationItem>> {
        val encounters: ApiResponse<List<RawPokemonEncounters>> =
            repository.getPokemonEncounters(name)
        return when (encounters) {
            is ApiResponse.Error -> ApiResponse.Error(encounters.message!!)
            is ApiResponse.Success -> {
                val limitedLocationList = encounters.data!!.map { it.location_area.name }
                val locations = locationRepository.getLocations(10, 0, "", limitedLocationList)
                when (locations) {
                    is ApiResponse.Error -> ApiResponse.Error(locations.message!!)
                    is ApiResponse.Success -> ApiResponse.Success(locations.data?.items!!)
                }
            }
        }
    }

    private suspend fun fetchMoves(moves: List<MoveWrapper>): ApiResponse<List<MoveItem>> {
        val limitedList = moves.map { move -> move.move.name }
        return movesRepository.getMoves(0, 10, "", limitedList).mapSuccess { it.items }
    }

    private suspend fun fetchAbilities(
        pokemonDetailsRawAbilities: List<RawAbility>
    ): ApiResponse<List<Ability>> = coroutineScope {
        val abilitiesIds = pokemonDetailsRawAbilities.map { it.ability.url.getIdFromUrl() }
        val deferredAbilities = abilitiesIds.map { id ->
            async { repository.getAbility(id) }
        }
        val abilityResponses = deferredAbilities.awaitAll()

        return@coroutineScope if (abilityResponses.ifAllSuccess()) {
            val mergedAbilities = abilityResponses.mapIndexed { index, response ->
                val rawAbilityDetails = response.data!!
                Ability(
                    pokemonDetailsRawAbilities[index].ability.name,
                    pokemonDetailsRawAbilities[index].ability.url,
                    pokemonDetailsRawAbilities[index].is_hidden,
                    rawAbilityDetails.effectEntries.first { it.language.name == "en" }.effect
                )
            }
            ApiResponse.Success(mergedAbilities)
        } else {
            ApiResponse.Error(abilityResponses.first { it is ApiResponse.Error }.message!!)
        }
    }
}