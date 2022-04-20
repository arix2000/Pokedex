package com.arix.pokedex.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.arix.pokedex.BuildConfig
import com.arix.pokedex.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class PokedexApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@PokedexApp)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    viewModelModule,
                    networkModule,
                    dataSourceModule,
                    useCaseModule
                )
            )
        }
    }
}