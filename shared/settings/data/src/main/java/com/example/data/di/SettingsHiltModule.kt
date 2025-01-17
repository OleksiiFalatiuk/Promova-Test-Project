package com.example.data.di

import com.example.data.repository.SettingsRepositoryImpl
import com.example.data.source.SettingsLocalDataSource
import com.example.data.source.SettingsLocalDataSourceImpl
import com.example.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SettingsHiltModule {
    @Binds
    fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    fun bindSettingsLocalDataSource(
        settingsLocalDataSourceImpl: SettingsLocalDataSourceImpl
    ): SettingsLocalDataSource
}