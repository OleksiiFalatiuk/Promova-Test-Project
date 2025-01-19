package com.promovatestproject.features.home.data.remote.source

import com.example.common.response.Response
import com.promovatestproject.features.home.data.remote.api.HomeApi
import com.promovatestproject.features.home.models.remote.HomeWrapperRemoteModel
import com.promovatestproject.features.home.models.remote.SortByTypeRemoteModel
import com.promovatestproject.network.utils.executeRequest
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRemoteDataSource {
    override suspend fun getFilms(page: Int): Response<HomeWrapperRemoteModel> =
        executeRequest {
            homeApi.getFilms(
                voteCount = 100,
                voteAverage = 7,
                sortBy = SortByTypeRemoteModel.PRIMARY_RELEASE_DATE_DESC.type,
                page = page
            )
        }
}