package com.arix.pokedex.features.moves.data.data_sources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arix.pokedex.extensions.DataStoreKeys.ALL_MOVES_LIST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovesLocalDataSource(private val dataStore: DataStore<Preferences>) {

    fun getMoveNames(): Flow<List<String>> {
        return dataStore.data.map { it[ALL_MOVES_LIST]?.toList() ?: emptyList() }
    }

    suspend fun saveMoveNames(names: List<String>) {
        dataStore.edit {
            it[ALL_MOVES_LIST] = names.toSet()
        }
    }
}