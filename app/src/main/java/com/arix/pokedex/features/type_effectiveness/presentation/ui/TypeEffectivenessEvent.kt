package com.arix.pokedex.features.type_effectiveness.presentation.ui

import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType

sealed class TypeEffectivenessEvent {
    class SelectTypeEvent(val selectableType: SelectableType): TypeEffectivenessEvent()
    class GetTypesEvent: TypeEffectivenessEvent()
}