package com.arix.pokedex.features.type_effectiveness.presentation

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.domain.model.DamageMultiplier
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.features.type_effectiveness.domain.model.TypeEffectiveness

val mockTypeEffectivenessList = listOf(
    TypeEffectiveness(
        type = SelectableType(type = Type("Normal")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to emptyList(),
            DamageMultiplier.HALF to listOf(Type("Rock"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Ghost")),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Water"), Type("Grass"),
                Type("Electric"), Type("Ice"), Type("Fighting"), Type("Poison"),
                Type("Ground"), Type("Flying"), Type("Psychic"), Type("Bug"),
                Type("Dragon"), Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Fire")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(
                Type("Grass"),
                Type("Bug"),
                Type("Ice"),
                Type("Steel")
            ),
            DamageMultiplier.HALF to listOf(
                Type("Fire"),
                Type("Water"),
                Type("Rock"),
                Type("Dragon")
            ),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Electric"), Type("Fighting"), Type("Poison"),
                Type("Ground"), Type("Flying"), Type("Psychic"), Type("Ghost"),
                Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Water")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Fire"), Type("Ground"), Type("Rock")),
            DamageMultiplier.HALF to listOf(Type("Water"), Type("Grass"), Type("Dragon")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Electric"), Type("Ice"), Type("Fighting"),
                Type("Poison"), Type("Flying"), Type("Psychic"), Type("Bug"),
                Type("Ghost"), Type("Dark"), Type("Steel"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Grass")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Water"), Type("Ground"), Type("Rock")),
            DamageMultiplier.HALF to listOf(
                Type("Fire"),
                Type("Grass"),
                Type("Poison"),
                Type("Flying"),
                Type("Bug"),
                Type("Dragon"),
                Type("Steel")
            ),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Electric"), Type("Ice"), Type("Fighting"),
                Type("Psychic"), Type("Ghost"), Type("Dark"), Type("Fairy")
            )
        )
    )
)