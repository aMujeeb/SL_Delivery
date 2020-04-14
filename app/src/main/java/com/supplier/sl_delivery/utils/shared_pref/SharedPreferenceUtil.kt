package com.supplier.sl_delivery.utils.shared_pref

import android.content.SharedPreferences

/**
 * Created by Aurora on 2020-04-12.
 */
class SharedPreferenceUtil {

    companion object {
        fun saveStringValue(prefs: SharedPreferences, key_name: String, value: String) {
            val editor = prefs.edit()
            editor.putString(key_name, value)
            editor.apply()
        }

        fun getStringValue(
            prefs: SharedPreferences,
            key_name: String,
            defaultValue: String
        ): String? {
            return prefs.getString(key_name, defaultValue)
        }
    }
}
