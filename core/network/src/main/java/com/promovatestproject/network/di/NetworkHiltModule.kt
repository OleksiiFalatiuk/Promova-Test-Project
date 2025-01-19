package com.promovatestproject.network.di

import com.promovatestproject.network.di.constants.NetworkConstants
import com.promovatestproject.network.interceptors.AccessTokenInterceptor
import com.promovatestproject.network.interceptors.accessTokenInterceptor
import com.promovatestproject.network.okhttp.OkHttpInitializer
import com.promovatestproject.network.retrofit.RetrofitInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkHiltModule {
    @Singleton
    @Provides
    fun provideAccessTokenInterceptor() =
        accessTokenInterceptor { NetworkConstants.ACCESS_TOKEN }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpInitializer.createGeneralOkHttpClient(enableLogging = false)

    @Singleton
    @Provides
    fun provideGeneralRetrofitClient(
        accessTokenInterceptor: AccessTokenInterceptor,
        okHttpClient: OkHttpClient
    ): Retrofit = RetrofitInitializer.createGeneralRetrofitClient(
        baseUrl = NetworkConstants.BASE_URL,
        enableLogging = true,
        okHttpClient = okHttpClient,
        accessTokenInterceptor
    )
}