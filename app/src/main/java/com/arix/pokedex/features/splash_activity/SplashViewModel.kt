package com.arix.pokedex.features.splash_activity

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.arix.pokedex.extensions.DataStoreKeys

class SplashViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    suspend fun downloadAndSaveInitialData() {
        saveThatAppWasOpened()
    }

    private suspend fun saveThatAppWasOpened() {
        dataStore.edit {
            it[DataStoreKeys.IS_INITIAL_APP_OPEN] = false
        }
    }
}