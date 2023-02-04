package com.arix.pokedex.features.items.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.items.domain.use_cases.GetItemNamesUseCase
import com.arix.pokedex.features.items.presentation.ui.ItemsScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val getItemNamesUseCase: GetItemNamesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ItemsScreenState())
    val state: State<ItemsScreenState> = _state

    private var getItemNamesJob: Job? = null

    init {
        getPokemonNames()
    }

    private fun getPokemonNames() {
        getItemNamesJob = viewModelScope.launch {
            getItemNamesUseCase().collect { _state.value = _state.value.copy(itemNames = it) }
        }
    }
}