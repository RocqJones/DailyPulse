package com.rocqjones.dailypulse.android.app_config

import android.app.Application
import android.util.Log
import com.rocqjones.dailypulse.android.di.databaseModule
import com.rocqjones.dailypulse.android.di.viewModelsModule
import com.rocqjones.dailypulse.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * A singleton class and only a single instance can be created
 * Stays alive until user kills the app
 */
class DailyPulseApp : Application() {

    private val TAG = "DailyPulseApp"

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        try {
            val module =  sharedKoinModule + viewModelsModule + databaseModule

            startKoin {
                androidContext(this@DailyPulseApp)
                modules(module) // init modules in Android
            }
        } catch (e: Exception) {
            Log.e(TAG, "initKoin: ${e.message}")
        }
    }

}