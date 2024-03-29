package com.arix.pokedex.features.move_details.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.arix.pokedex.core.Constants.FlavorTextEntriesVersion.MOVE_DESCRIPTION_VERSION
import com.arix.pokedex.core.Constants.Language.ENGLISH_LANGUAGE_CODE
import com.arix.pokedex.core.Constants.MoveScreen.EFFECT_CHANCE_ARG
import com.arix.pokedex.extensions.toSentenceCase
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.moves.domain.model.move.FlavorTextEntry
import com.arix.pokedex.features.moves.domain.model.move.Meta
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonBasicData

data class UiMove(
    val id: Int,
    val name: String,
    val accuracy: Int?,
    val power: Int,
    val pp: Int,
    val priority: Int,
    val damageClass: DamageClassEnum,
    val effectChance: String,
    val effectDesc: String?,
    val description: String?,
    val meta: Meta,
    val stat_changes: List<UiStatChange>,
    val target: String,
    val type: Type,
    val typeColor: Color,
    val learned_by_pokemon: List<PokemonBasicData>
) {
    companion object {
        fun fromRaw(raw: RawMove): UiMove {
            with(raw) {
                val effectChance = effect_chance ?: 100
                return UiMove(
                    id,
                    name.toSentenceCase(),
                    accuracy,
                    power ?: 0,
                    pp,
                    priority,
                    DamageClassEnum.valueOf(damage_class.name.uppercase()),
                    "$effectChance%",
                    effect_entries.firstOrNull { it.language.name == ENGLISH_LANGUAGE_CODE }?.effect
                        ?.replace(EFFECT_CHANCE_ARG, effectChance.toString()),
                    getMoveDescription(this),
                    meta ?: Meta.EMPTY,
                    stat_changes.map { UiStatChange(it.change, it.stat.name) },
                    target.name.replace("-", " ").capitalize(Locale.current),
                    Type(type.name),
                    Type(type.name).getTypeColor(),
                    learned_by_pokemon
                )
            }
        }

        private fun getMoveDescription(move: RawMove) = with(move) {
            return@with (flavor_text_entries.firstOrNull { whereEngOmegaRubyVersion(it) }?.flavor_text
                ?: flavor_text_entries.firstOrNull { it.language.name == ENGLISH_LANGUAGE_CODE }?.flavor_text)
                ?.replace("\n", " ")
        }

        private fun whereEngOmegaRubyVersion(it: FlavorTextEntry) =
            it.language.name == ENGLISH_LANGUAGE_CODE && it.version_group.name == MOVE_DESCRIPTION_VERSION

        val EMPTY = UiMove(
            0, "", 0, 0, 0, 0, DamageClassEnum.STATUS,
            "", "", "", Meta.EMPTY, listOf(), "", Type(""),
            Color.Black, listOf(),
        )
    }
}
