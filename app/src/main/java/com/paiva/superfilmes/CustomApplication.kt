package com.paiva.superfilmes

import android.app.Application
import com.paiva.superfilmes.di.viewModelModules
import com.superfilmes.core.di.apiModule
import com.superfilmes.core.di.connectionModule
import com.superfilmes.core.di.usesCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CustomApplication: Application() {
    
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@CustomApplication)
            modules(listOf(
                viewModelModules, usesCase, apiModule, connectionModule))
        }
    }
}