package com.arix.pokedex.features.pokemon_list.data.data_sources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arix.pokedex.extensions.DataStoreKeys.ALL_POKEMON_NAMES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonLocalDataSource(private val dataStore: DataStore<Preferences>) {

    fun getPokemonNames(): Flow<List<String>> {
        return dataStore.data.map { it[ALL_POKEMON_NAMES]?.toList() ?: emptyList() }
    }
}