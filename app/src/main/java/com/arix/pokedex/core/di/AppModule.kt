package com.arix.pokedex.core.di

import android.content.Context
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.extensions.dataStore
import com.arix.pokedex.utils.InitialAppOpenManager
import org.koin.dsl.module

val appModule = module {
    single { get<Context>().dataStore }

    single { Navigator() }

    factory { InitialAppOpenManager(get()) }
}