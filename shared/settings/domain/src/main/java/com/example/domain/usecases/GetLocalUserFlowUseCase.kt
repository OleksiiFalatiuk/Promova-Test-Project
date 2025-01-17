package com.example.domain.usecases

import com.example.domain.repository.SettingsRepository
import javax.inject.Inject

class GetLocalUserFlowUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator fun invoke() = settingsRepository.getUserFlow()
}