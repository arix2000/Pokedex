package com.arix.pokedex.features.pokemon_details.domain.model.species

import com.arix.pokedex.core.Constants.Language.ENGLISH_LANGUAGE_CODE
import com.arix.pokedex.core.Constants.PokemonDetailsScreen.DESIRED_DESCRIPTION_VERSION
import com.arix.pokedex.core.Constants.PokemonDetailsScreen.NO_DESCRIPTION
import com.arix.pokedex.extensions.clearEndOfLineEscapeSequences
import com.arix.pokedex.extensions.getIdFromUrl

data class PokemonSpecies(
    val base_happiness: Int,
    val capture_rate: Int,
    val color: Color,
    val egg_groups: List<EggGroup>,
    val evolution_chain: EvolutionChain,
    val evolves_from_species: Any,
    val flavor_text_entries: List<FlavorTextEntry>,
    val form_descriptions: List<Any>,
    val forms_switchable: Boolean,
    val gender_rate: Int,
    val genera: List<Genera>,
    val generation: Generation,
    val growth_rate: GrowthRate,
    val habitat: Habitat?,
    val has_gender_differences: Boolean,
    val hatch_counter: Int,
    val id: Int,
    val is_baby: Boolean,
    val is_legendary: Boolean,
    val is_mythical: Boolean,
    val name: String,
    val names: List<Name>,
    val order: Int,
    val pal_park_encounters: List<Any>,
    val pokedex_numbers: List<PokedexNumber>,
    val shape: Shape,
    val varieties: List<Variety>
) {
    fun getDescription(): String {
        val enTextEntries = flavor_text_entries.filter { it.language.name == ENGLISH_LANGUAGE_CODE }
        if (enTextEntries.isEmpty())
            return NO_DESCRIPTION

        val desiredEntry = enTextEntries.find { it.version.name == DESIRED_DESCRIPTION_VERSION }
        val finalEntry = (desiredEntry?.flavor_text ?: enTextEntries.last().flavor_text)
        return finalEntry.clearEndOfLineEscapeSequences()
    }

    fun getGenerationName(): String {
        val genNumberConverted = when (generation.url.getIdFromUrl()) {
            1 -> "I"
            2 -> "II"
            3 -> "III"
            4 -> "IV"
            5 -> "V"
            6 -> "VI"
            7 -> "VII"
            8 -> "VIII"
            else -> return "No generation"
        }
        return "Generation $genNumberConverted"
    }
}