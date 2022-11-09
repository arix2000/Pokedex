package com.arix.pokedex.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arix.pokedex.extensions.DataStoreKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

class InitialAppOpenManager(private val dataStore: DataStore<Preferences>) {

    fun ifAppOpenedFirstTime(): Flow<Boolean> = dataStore.data.map {
        return@map it[DataStoreKeys.IS_INITIAL_APP_OPEN] ?: true
    }
}