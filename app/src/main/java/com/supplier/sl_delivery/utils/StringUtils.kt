package com.supplier.sl_delivery.utils

/**
 * Created by Aurora on 2020-04-10.
 */
class StringUtils {
    companion object {
        fun validateEmail(email : String) : Boolean {
            val ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
            val p = java.util.regex.Pattern.compile(ePattern)
            val m = p.matcher(email)
            return !m.matches()
        }
    }
}
