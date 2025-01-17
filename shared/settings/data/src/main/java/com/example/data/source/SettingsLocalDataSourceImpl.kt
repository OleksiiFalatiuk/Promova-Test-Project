package com.example.data.source

import com.example.data.store.SettingsDataStore
import com.promovatestproject.shared.user.models.local.UserLocalModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsLocalDataSourceImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsLocalDataSource {
    override suspend fun updateUser(block: suspend (settings: UserLocalModel?) -> UserLocalModel?) =
        settingsDataStore.updateUser(block)

    override fun getUserFlow(): Flow<UserLocalModel?> = settingsDataStore.getUserFlow()
}