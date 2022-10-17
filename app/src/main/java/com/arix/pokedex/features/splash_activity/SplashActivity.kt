package com.arix.pokedex.features.splash_activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.arix.pokedex.features.MainActivity
import com.arix.pokedex.utils.InitialAppOpenManager
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            val initialAppOpenManager = get<InitialAppOpenManager>()
            val viewModel: SplashViewModel = getViewModel()
            initialAppOpenManager.ifAppOpenedFirstTime().collect { isOpenedFirstTime ->
                if (isOpenedFirstTime)
                    viewModel.downloadAndSaveInitialData()

                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}