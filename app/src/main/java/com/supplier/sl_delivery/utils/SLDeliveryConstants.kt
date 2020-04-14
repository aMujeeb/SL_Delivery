package com.supplier.sl_delivery.utils

/**
 * Created by Aurora on 2020-03-30.
 */
class SLDeliveryConstants {
    companion object {
        const val APP_TAG                               = "SL_DEL"
        val AUTH_TOKEN_CONTENT_TYPE                     = "Content-Type"
        val AUTH_TOKEN_CONTENT_TYPE_VALUE_JSON          = "application/json"
        val AUTH_TOKEN_CONTENT_TYPE_VALUE_TEXT          = "text/plain"
        val AUTH_CONNECTION_TYPE                        = "Connection"
        val AUTH_CONNECTION_TYPE_VALUE                  = "keep-alive"
        val AUTH_ACCEPT                                 = "Accept"
        val AUTH_ACCEPT_VALUE                           = "*/*"

        const val USERNAME_ERROR : Int                  = 2
        const val PASSWORD_ERROR : Int                  = 3
        const val BUSINESS_NAME_ERROR : Int             = 4
        const val OWNER_NAME_ERROR : Int                = 5
        const val ADDRESS_ERROR : Int                   = 6
        const val MOBILE_NUMBER_ERROR : Int             = 7
        const val BUSINESS_REG_ERROR : Int              = 8
        const val PASSWORD_CONFIRM_ERROR : Int          = 9
        const val PASSWORD_NOT_MATCHING_ERROR : Int     = 10

        const val SHARED_PREFERENCE_KEY                 = "SL_DELIVERY"
        const val ACCESS_TOKEN                          = "access_token"

        const val SUCCESS : Int                         = 200
        const val UN_SUCCESS : Int                      = 401
        const val ERROR                                 = "ERROR"
    }
}
