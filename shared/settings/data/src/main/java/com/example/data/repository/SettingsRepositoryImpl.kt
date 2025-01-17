package com.example.data.repository

import com.example.data.source.SettingsLocalDataSource
import com.example.domain.repository.SettingsRepository
import com.example.shared.user.models.domain.UserDomainModel
import com.promovatestproject.shared.user.models.local.toDomainModel
import com.promovatestproject.shared.user.models.local.toLocalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {
    override fun getUserFlow(): Flow<UserDomainModel?> = settingsLocalDataSource.getUserFlow()
        .map { it?.toDomainModel() }

    override suspend fun updateLocalUser(
        block: suspend (user: UserDomainModel?) -> UserDomainModel?
    ) = settingsLocalDataSource.updateUser { localUser ->
        block(localUser?.toDomainModel())?.toLocalModel()
    }
}