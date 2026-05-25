package com.arix.pokedex.features.type_effectiveness.domain.model

enum class DamageMultiplier(val multiplier: Float) {
    DOUBLE(2f), HALF(0.5f), NO_DAMAGE(0f), NORMAL(1f)
}