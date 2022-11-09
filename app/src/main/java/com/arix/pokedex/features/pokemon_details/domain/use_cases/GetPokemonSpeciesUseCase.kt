package com.arix.pokedex.features.pokemon_details.domain.use_cases

import com.arix.pokedex.features.pokemon_details.domain.PokemonDetailsRepository
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.utils.Resource

class GetPokemonSpeciesUseCase(private val repository: PokemonDetailsRepository) {
    suspend operator fun invoke(name: String): Resource<PokemonSpecies> {
        return repository.getPokemonSpecies(name)
    }
}