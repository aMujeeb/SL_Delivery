package com.supplier.sl_delivery.ui.actions

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created by Aurora on 2020-03-31.
 */
open class ActionLiveData<T>  :MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }
        super.observe(owner, Observer {
            if (it != null) {
                observer.onChanged(it)
                // We set the value to null straight after emitting the change to the observer
                value = null
            } else {
                return@Observer
            }
        })
    }

    @MainThread
    fun sendAction(data: T) {
        value = data
    }
}
