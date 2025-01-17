package com.example.domain.repository

import com.example.shared.user.models.domain.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getUserFlow(): Flow<UserDomainModel?>
    suspend fun updateLocalUser(block: suspend (user: UserDomainModel?) -> UserDomainModel?)
}