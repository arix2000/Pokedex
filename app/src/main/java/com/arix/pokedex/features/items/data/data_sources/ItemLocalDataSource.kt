package com.arix.pokedex.features.items.data.data_sources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arix.pokedex.extensions.DataStoreKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemLocalDataSource(private val dataStore: DataStore<Preferences>) {
    fun getItemNames(): Flow<List<String>> {
        return dataStore.data.map { it[DataStoreKeys.ALL_ITEM_LIST]?.toList() ?: emptyList() }
    }

    suspend fun saveItemNames(names: List<String>) {
        dataStore.edit {
            it[DataStoreKeys.ALL_ITEM_LIST] = names.toSet()
        }
    }
}