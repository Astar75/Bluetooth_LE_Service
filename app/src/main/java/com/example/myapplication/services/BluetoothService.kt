package com.example.myapplication.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BluetoothService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return LocalBinder()
    }

    inner class LocalBinder: Binder() {
        fun getService() = this@BluetoothService
    }
}
