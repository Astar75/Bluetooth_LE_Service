package com.example.myapplication.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.scan.ScanFilter
import com.polidea.rxandroidble2.scan.ScanResult
import com.polidea.rxandroidble2.scan.ScanSettings
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.get

class BluetoothService : Service() {

    private val bluetoothClient: RxBleClient = get()
    private var scanDisposable: Disposable? = null
    private val isScanning: Boolean
        get() = scanDisposable != null

    override fun onCreate() {
        super.onCreate()
        Log.d("Service", "Service started!")
    }

    override fun onBind(intent: Intent): IBinder {
        return LocalBinder()
    }

    inner class LocalBinder : Binder() {
        fun getService() = this@BluetoothService
    }

    private fun scanBleDevices(): Observable<ScanResult> {
        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .build()

        val scanFilter = ScanFilter.Builder()
            .build()

        return bluetoothClient.scanBleDevices(scanSettings, scanFilter)
    }

    fun startScan() {
        if (isScanning) {
            stopScan()
        }

        if (bluetoothClient.isScanRuntimePermissionGranted) {
            scanBleDevices()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { stopScan() }
                .subscribe({}, {})
                .let { scanDisposable = it }
        }
    }

    fun stopScan() {
        scanDisposable = null
    }
}
