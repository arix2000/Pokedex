package com.arix.pokedex.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(20.dp)
)

object Shapes {
    val medium = RoundedCornerShape(12.dp)
    val large = RoundedCornerShape(22.dp)

    val bottomSheet = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)
}