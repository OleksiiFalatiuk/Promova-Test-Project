package com.promovatestproject.features.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.common.pagination.remotePagingSource
import com.example.common.response.map
import com.promovatestproject.features.home.data.remote.source.HomeRemoteDataSource
import com.promovatestproject.features.home.domain.repository.HomeRepository
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import com.promovatestproject.features.home.models.remote.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    private val homeRemotePagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        initialLoadSize = INITIAL_LOAD_SIZE
    )

    override fun getHome(id: String, type: String): Flow<PagingData<HomeDomainModel>> =
        Pager(homeRemotePagingConfig) {
            remotePagingSource { page, pageSize ->
                homeRemoteDataSource.getHome(
                    id = id,
                    type = type,
                    page = page,
                    pageSize = pageSize
                ).map { it.items }
            }
        }.flow.map { pagingData ->
            pagingData.map { homeRemoteModel ->
                homeRemoteModel.toDomainModel()
            }
        }

    companion object {
        private const val PAGE_SIZE = 10
        private const val INITIAL_LOAD_SIZE = 10
    }
}