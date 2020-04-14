package com.supplier.sl_delivery.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Aurora on 2020-04-10.
 */
class APIRestFactory {
    companion object {
        var url : String = "https://merchant-6sxr6adwza-de.a.run.app/"
        fun apiInstance(headerInterceptor: WebApiRequestInterceptor) : IAPIClientService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(httpLoggingInterceptor)

            builder.addInterceptor(headerInterceptor)
            builder.readTimeout(180, TimeUnit.SECONDS)
            builder.connectTimeout(180, TimeUnit.SECONDS)
            builder.writeTimeout(180, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(builder.build())
                .build()

            return retrofit.create(IAPIClientService::class.java!!)
        }
    }
}
