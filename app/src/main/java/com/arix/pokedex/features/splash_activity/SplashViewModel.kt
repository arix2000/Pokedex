package com.arix.pokedex.features.splash_activity

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.arix.pokedex.core.Constants.SplashScreen.MAX_LIMIT
import com.arix.pokedex.extensions.DataStoreKeys
import com.arix.pokedex.features.moves.domain.use_cases.SaveMoveNamesUseCase
import com.arix.pokedex.features.poke_list.domain.use_cases.GetPokemonListUseCase
import com.arix.pokedex.features.poke_list.domain.use_cases.SavePokemonNamesUseCase

class SplashViewModel(
    private val getRawMoveList: GetRawMoveList,
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val savePokemonNamesUseCase: SavePokemonNamesUseCase,
    private val saveMoveNamesUseCase: SaveMoveNamesUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    suspend fun downloadAndSaveInitialData() {
        val moveList = getRawMoveList(0, MAX_LIMIT).data
        val pokemonList = getPokemonListUseCase(0, MAX_LIMIT).data
        if (moveList == null || pokemonList == null)
            return

        saveMoveNamesUseCase(moveList.moveLinks.map { it.name })
        savePokemonNamesUseCase(pokemonList.pokemonList.map { it.name })
        saveThatAppWasOpened()
    }

    private suspend fun saveThatAppWasOpened() {
        dataStore.edit {
            it[DataStoreKeys.IS_INITIAL_APP_OPEN] = false
        }
    }
}