package com.example.app_events_around

import android.app.Application
import com.example.app_events_around.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}