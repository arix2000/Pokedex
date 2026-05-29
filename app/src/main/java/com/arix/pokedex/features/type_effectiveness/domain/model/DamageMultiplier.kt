package com.arix.pokedex.features.type_effectiveness.domain.model

enum class DamageMultiplier(
    val baseMultiplier: Double, multiplierRange: ClosedRange<Double>
) {
    DOUBLE(baseMultiplier = 2.0, multiplierRange = 2.0..4.0),
    HALF(baseMultiplier = 0.5, multiplierRange = 0.25..0.5),
    NO_DAMAGE(baseMultiplier = 0.0, multiplierRange = 0.0..0.0),
    NORMAL(baseMultiplier = 1.0, multiplierRange = 1.0..1.0)
}