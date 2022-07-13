package com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain

import com.arix.pokedex.features.pokemon_details.domain.model.RawEvolutionStep

data class PokemonEvolutionChain(
    val baby_trigger_item: Any,
    val chain: Chain,
    val id: Int
) {
    fun getPokemonInChainNames(): List<RawEvolutionStep> {
        val evolutionAmount = when {
            chain.evolves_to.isEmpty() -> 1
            chain.evolves_to.first().evolves_to.isEmpty() -> 2
            chain.evolves_to.first().evolves_to.first().evolves_to.isEmpty() -> 3
            else -> 0
        }

        return mutableListOf<RawEvolutionStep>().apply {
            when (evolutionAmount) {
                1 -> add(RawEvolutionStep(listOf(chain.species.name), chain.evolution_details))
                2 -> {
                    add(RawEvolutionStep(listOf(chain.species.name), chain.evolution_details))
                    add(RawEvolutionStep(getNamesFromFirstEvolution(), getDetailsFromFirstEvolution()))
                }
                3 -> {
                    add(RawEvolutionStep(listOf(chain.species.name), chain.evolution_details))
                    add(RawEvolutionStep(getNamesFromFirstEvolution(), getDetailsFromFirstEvolution()))
                    add(RawEvolutionStep(getNamesFromSecondEvolution(), getDetailsFromSecondEvolution()))
                }
            }
        }
    }

    private fun getNamesFromFirstEvolution(): List<String> {
        return chain.evolves_to.map { it.species.name }
    }

    private fun getDetailsFromFirstEvolution(): List<EvolutionDetail> {
        return chain.evolves_to.flatMap { it.evolution_details }
    }

    private fun getNamesFromSecondEvolution(): List<String> {
        val pokemonNames: MutableList<String> = mutableListOf()
        chain.evolves_to.forEach { previousEvolution ->
            with(previousEvolution.evolves_to) {
                pokemonNames.addAll(map { it.species.name })
            }
        }
        return pokemonNames
    }

    private fun getDetailsFromSecondEvolution(): List<EvolutionDetail> {
        val evolutionDetails = mutableListOf<EvolutionDetail>()
        chain.evolves_to.forEach { previousEvolution ->
            with(previousEvolution.evolves_to) {
                evolutionDetails.addAll(flatMap { it.evolution_details })
            }
        }
        return evolutionDetails
    }
}