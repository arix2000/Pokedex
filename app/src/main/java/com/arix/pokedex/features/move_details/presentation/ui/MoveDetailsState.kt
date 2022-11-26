package com.arix.pokedex.features.move_details.presentation.ui

import com.arix.pokedex.features.move_details.domain.model.UiMove

data class MoveDetailsState(
    val move: UiMove? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)