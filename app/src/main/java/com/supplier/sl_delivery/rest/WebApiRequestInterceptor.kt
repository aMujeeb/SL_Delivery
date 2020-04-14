package com.supplier.sl_delivery.rest

import com.supplier.sl_delivery.utils.SLDeliveryConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Aurora on 2020-04-10.
 */
class WebApiRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request : Request
        val builder = chain.request().newBuilder()
        builder.addHeader(SLDeliveryConstants.AUTH_TOKEN_CONTENT_TYPE, SLDeliveryConstants.AUTH_TOKEN_CONTENT_TYPE_VALUE_JSON)
        builder.addHeader(SLDeliveryConstants.AUTH_TOKEN_CONTENT_TYPE, SLDeliveryConstants.AUTH_TOKEN_CONTENT_TYPE_VALUE_TEXT)
        builder.addHeader(SLDeliveryConstants.AUTH_CONNECTION_TYPE, SLDeliveryConstants.AUTH_CONNECTION_TYPE_VALUE)
        request = builder.build()
        return chain.proceed(request)
    }
}
