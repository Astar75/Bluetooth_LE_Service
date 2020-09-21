package com.example.myapplication.di

import android.content.Context
import com.polidea.rxandroidble2.RxBleClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val bluetoothModule = module {
    single { provideBluetoothModule(androidContext()) }
}

fun provideBluetoothModule(context: Context): RxBleClient {
    return RxBleClient.create(context)
}