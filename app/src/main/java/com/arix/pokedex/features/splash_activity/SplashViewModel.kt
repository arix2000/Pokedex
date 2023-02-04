package com.arix.pokedex.features.splash_activity

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.arix.pokedex.core.Constants.SplashScreen.MAX_LIMIT
import com.arix.pokedex.extensions.DataStoreKeys
import com.arix.pokedex.features.items.domain.use_cases.GetItemListUseCase
import com.arix.pokedex.features.items.domain.use_cases.SaveItemNamesUseCase
import com.arix.pokedex.features.moves.domain.use_cases.GetMoveListUseCase
import com.arix.pokedex.features.moves.domain.use_cases.SaveMoveNamesUseCase
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.features.pokemon_list.domain.use_cases.SavePokemonNamesUseCase

class SplashViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val savePokemonNamesUseCase: SavePokemonNamesUseCase,
    private val getMoveListUseCase: GetMoveListUseCase,
    private val saveMoveNamesUseCase: SaveMoveNamesUseCase,
    private val getItemListUseCase: GetItemListUseCase,
    private val saveItemNamesUseCase: SaveItemNamesUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    suspend fun downloadAndSaveInitialData() {
        val moveList = getMoveListUseCase(0, MAX_LIMIT).data
        val pokemonList = getPokemonListUseCase(0, MAX_LIMIT).data
        val itemList = getItemListUseCase(0, MAX_LIMIT).data
        if (moveList == null || pokemonList == null || itemList == null)
            return

        saveMoveNamesUseCase(moveList.moveLinks.map { it.name })
        savePokemonNamesUseCase(pokemonList.pokemonList.map { it.name })
        saveItemNamesUseCase(itemList.items.map { it.name })
        saveThatAppWasOpened()
    }

    private suspend fun saveThatAppWasOpened() {
        dataStore.edit {
            it[DataStoreKeys.IS_INITIAL_APP_OPEN] = false
        }
    }
}