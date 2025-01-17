package com.promovatestproject.network.di

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
    @Provides
    fun provideAccessTokenInterceptor() =
        accessTokenInterceptor { "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MGE1NzY4ODRjNGM4M2JiYTVhYTEyZjRhNzEwMzhhZiIsIm5iZiI6MTYzNzYwODQ1My42OTQwMDAyLCJzdWIiOiI2MTliZWMwNWJjMmNiMzAwNDI4MGIyNGUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.j7Vi1RFXq96ZItNmF4ZrQgewPCA_J0Qrp_Hv6OJEWcg" }

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
        baseUrl = "https://api.themoviedb.org/3/",
        enableLogging = true,
        okHttpClient = okHttpClient,
        accessTokenInterceptor
    )
}