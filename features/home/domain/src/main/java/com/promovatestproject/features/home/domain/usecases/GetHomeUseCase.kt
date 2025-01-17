package com.promovatestproject.features.home.domain.usecases

import androidx.paging.PagingData
import com.promovatestproject.features.home.domain.repository.HomeRepository
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(id: String, type: String): Flow<PagingData<HomeDomainModel>> =
        homeRepository.getHome(id, type)
}