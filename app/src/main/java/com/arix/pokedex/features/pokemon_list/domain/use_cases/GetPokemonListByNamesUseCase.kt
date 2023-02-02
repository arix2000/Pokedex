package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class GetPokemonListByNamesUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(names: List<String>): List<ApiResponse<PokemonDetails>> {
        val pokemonResponses = names
            .map { CoroutineScope(Dispatchers.IO).async { repository.getPokemon(it) } }
        return pokemonResponses.awaitAll()
            .map { responses -> responses.mapSuccess { PokemonDetails.fromRaw(it) } }
    }
}