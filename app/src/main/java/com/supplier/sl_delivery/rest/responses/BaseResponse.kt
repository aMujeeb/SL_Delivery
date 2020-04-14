package com.supplier.sl_delivery.rest.responses

import com.supplier.sl_delivery.enums.Status

/**
 * Created by Aurora on 2020-04-11.
 */
open class BaseResponse {
    var responseStatus : Int? = null
    var status : Status? = null
    var message : String? = null
}
