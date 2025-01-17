package com.promovatestproject.features.home.domain.repository

import androidx.paging.PagingData
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import kotlinx.coroutines.flow.Flow

// TODO: This code is generated from a template. You need to add your implementation
interface HomeRepository {
    fun getHome(id: String, type: String): Flow<PagingData<HomeDomainModel>>
}