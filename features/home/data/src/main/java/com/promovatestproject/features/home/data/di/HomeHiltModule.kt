package com.promovatestproject.features.home.data.di

import com.promovatestproject.features.home.data.remote.api.HomeApi
import com.promovatestproject.features.home.data.remote.source.HomeRemoteDataSource
import com.promovatestproject.features.home.data.remote.source.HomeRemoteDataSourceImpl
import com.promovatestproject.features.home.data.repository.HomeRepositoryImpl
import com.promovatestproject.features.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal interface HomeHiltModule {
    @Binds
    fun bindHomeRemoteDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    companion object {
        @Provides
        fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)
    }
}