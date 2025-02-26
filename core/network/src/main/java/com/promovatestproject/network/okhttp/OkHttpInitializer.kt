package com.promovatestproject.network.okhttp

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpInitializer {
    private const val DEFAULT_TIMEOUT = 60L

    fun createGeneralOkHttpClient(
        enableLogging: Boolean,
        okHttpClient: OkHttpClient? = null,
        vararg interceptors: Interceptor
    ): OkHttpClient {
        val builder = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
            .callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
            }

        if (enableLogging) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            builder.addInterceptor(
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }

        return builder.build()
    }
}