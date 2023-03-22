package com.arix.pokedex.features.pokemon_details.domain.use_cases

import com.arix.pokedex.features.pokemon_details.domain.PokemonDetailsRepository
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.utils.ApiResponse

class GetPokemonSpeciesUseCase(private val repository: PokemonDetailsRepository) {
    suspend operator fun invoke(name: String): ApiResponse<PokemonSpecies> {
        return repository.getPokemonSpecies(name)
    }
}