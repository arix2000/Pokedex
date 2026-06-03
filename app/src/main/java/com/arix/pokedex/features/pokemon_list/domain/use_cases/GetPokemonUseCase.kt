package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.extensions.ifAllSuccess
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.features.pokemon_list.domain.model.RawAbilityDetails
import com.arix.pokedex.features.pokemon_list.domain.model.details.Ability
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetPokemonUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(name: String): ApiResponse<PokemonDetails> {
        return repository.getPokemon(name).mapSuccessAsync { rawPokemonDetails ->
            coroutineScope {
                val abilitiesIds = rawPokemonDetails.abilities.map { it.ability.url.getIdFromUrl() }
                val deferredAbilities = abilitiesIds.map { id ->
                    async {
                        repository.getAbility(id)
                    }
                }
                val abilityResponses = deferredAbilities.awaitAll()
                val abilities: ApiResponse<List<RawAbilityDetails>> =
                    if (abilityResponses.ifAllSuccess())
                        ApiResponse.Success(abilityResponses.map { response -> response.data!! })
                    else
                        ApiResponse.Error(abilityResponses.first { it is ApiResponse.Error }.message!!)


                return@coroutineScope when (abilities) {
                    is ApiResponse.Success -> {
                        val pokemonDetailsRawAbilities = rawPokemonDetails.abilities
                        val mergedAbilities =
                            abilities.data!!.mapIndexed { index, rawAbilityDetails ->
                                Ability(
                                    pokemonDetailsRawAbilities[index].ability.name,
                                    pokemonDetailsRawAbilities[index].ability.url,
                                    pokemonDetailsRawAbilities[index].is_hidden,
                                    rawAbilityDetails.effectEntries.first { it.language.name == "en" }.effect
                                )
                            }
                        PokemonDetails.fromRaw(rawPokemonDetails, mergedAbilities)
                    }

                    is ApiResponse.Error -> PokemonDetails.fromRaw(rawPokemonDetails, emptyList())
                }

            }
        }
    }
}