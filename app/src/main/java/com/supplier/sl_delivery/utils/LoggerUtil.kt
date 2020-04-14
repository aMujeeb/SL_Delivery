package com.supplier.sl_delivery.utils

import android.util.Log
import com.supplier.sl_delivery.utils.SLDeliveryConstants.Companion.APP_TAG

/**
 * Created by Aurora on 2020-03-30.
 */
class LoggerUtil {
    companion object {
        fun logMessage(message : String){
            Log.d(APP_TAG, message)
        }
    }
}
