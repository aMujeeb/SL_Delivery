package com.supplier.sl_delivery.utils.shared_pref

import android.content.Context
import android.content.SharedPreferences
import com.supplier.sl_delivery.utils.SLDeliveryConstants

/**
 * Created by Aurora on 2020-04-12.
 */
class SharedPreferenceGenerator {
    companion object {
        fun getInstance(context: Context) : SharedPreferences {
            return context.getSharedPreferences(
                SLDeliveryConstants.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        }
    }
}
