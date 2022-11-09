package com.arix.pokedex.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("store")

object DataStoreKeys {
   val ALL_POKEMON_NAMES = stringSetPreferencesKey("all_pokemon_names")
   val ALL_MOVES_LIST = stringSetPreferencesKey("all_moves_list")
   val IS_INITIAL_APP_OPEN = booleanPreferencesKey("is_initial_app_open")
}



