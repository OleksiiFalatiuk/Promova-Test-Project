package com.promovatestproject.features.home.domain.usecases

import com.promovatestproject.features.home.domain.repository.HomeRepository
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikedFilmsFromLocalUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<List<HomeDomainModel>> = homeRepository.getLikedFilmsFlow()
}