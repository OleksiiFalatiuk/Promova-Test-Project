package com.example.domain.usecases

import com.example.domain.repository.SettingsRepository
import com.example.shared.user.models.domain.UserDomainModel
import javax.inject.Inject

class UpdateLocalUserUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(block: suspend (user: UserDomainModel?) -> UserDomainModel?) =
        settingsRepository.updateLocalUser(block)
}