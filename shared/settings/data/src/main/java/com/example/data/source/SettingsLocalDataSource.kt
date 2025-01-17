package com.example.data.source

import com.promovatestproject.shared.user.models.local.UserLocalModel
import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataSource {
    suspend fun updateUser(block: suspend (settings: UserLocalModel?) -> UserLocalModel?)
    fun getUserFlow(): Flow<UserLocalModel?>
}