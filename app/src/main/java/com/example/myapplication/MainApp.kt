package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.bluetoothModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(bluetoothModule)
        }
    }
}