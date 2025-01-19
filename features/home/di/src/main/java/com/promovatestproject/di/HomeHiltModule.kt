package com.promovatestproject.di

import com.promovatestproject.database.PromovaTestDatabase
import com.promovatestproject.features.home.data.local.dao.FilmDao
import com.promovatestproject.features.home.data.local.source.HomeLocalDataSource
import com.promovatestproject.features.home.data.local.source.HomeLocalDataSourceImpl
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
    fun bindHomeLocalDataSource(homeLocalDataSourceImpl: HomeLocalDataSourceImpl): HomeLocalDataSource

    @Binds
    fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    companion object {
        @Provides
        fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)

        @Provides
        fun provideFilmDao(database: PromovaTestDatabase): FilmDao = database.getFilmDao()
    }
}