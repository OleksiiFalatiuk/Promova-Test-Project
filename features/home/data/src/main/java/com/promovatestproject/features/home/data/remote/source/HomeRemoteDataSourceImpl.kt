package com.promovatestproject.features.home.data.remote.source

import com.example.common.response.Response
import com.promovatestproject.features.home.data.remote.api.HomeApi
import com.promovatestproject.features.home.models.remote.HomeWrapperRemoteModel
import com.promovatestproject.network.utils.executeRequest
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRemoteDataSource {
    override suspend fun getFilms(page: Int): Response<HomeWrapperRemoteModel> =
        executeRequest {
            homeApi.getFilms(
                voteCount = VOTE_COUNT,
                voteAverage = VOTE_AVERAGE,
                sortBy = PRIMARY_RELEASE_DATE_DESC,
                page = page
            )
        }

    companion object {
        const val VOTE_COUNT = 100
        const val VOTE_AVERAGE = 7
        const val PRIMARY_RELEASE_DATE_DESC = "primary_release_date.desc"
    }
}