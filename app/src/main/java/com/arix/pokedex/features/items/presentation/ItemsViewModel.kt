package com.arix.pokedex.features.items.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.use_cases.GetItemDetailsUseCase
import com.arix.pokedex.features.items.domain.use_cases.GetItemListUseCase
import com.arix.pokedex.features.items.presentation.ui.ItemEvent
import com.arix.pokedex.features.items.presentation.ui.ItemsScreenState
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val getItemListUseCase: GetItemListUseCase,
    private val getItemUseCase: GetItemDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ItemsScreenState())
    val state: State<ItemsScreenState> = _state

    private var getItemJob: Job? = null

    fun invokeEvent(event: ItemEvent) {
        when (event) {
            is ItemEvent.GetItemDetails -> getItem(event.itemId)
        }
    }

    suspend fun getItems(offset: Int, searchQuery: String): ApiResponse<Page<Item>> {
        return getItemListUseCase(offset, searchQuery = searchQuery)
    }

    private fun getItem(itemId: String) {
        getItemJob?.cancel()
        getItemJob = viewModelScope.launch {
            _state.run {
                value = value.copy(isLoading = true)
                val response = getItemUseCase(itemId)
                value = when(response) {
                    is ApiResponse.Success -> value.copy(
                        itemDetails = response.data!!,
                        isLoading = false
                    )
                    is ApiResponse.Error -> value.copy(
                        errorMessage = response.message,
                        isLoading = false
                    )
                }
            }
        }
    }

}