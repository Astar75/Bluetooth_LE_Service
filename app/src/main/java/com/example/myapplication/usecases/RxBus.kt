package com.example.myapplication.usecases

import com.polidea.rxandroidble2.RxBleDevice
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class RxEvent {
    data class EventAddDevices(val list: List<RxBleDevice>)
}

object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

}