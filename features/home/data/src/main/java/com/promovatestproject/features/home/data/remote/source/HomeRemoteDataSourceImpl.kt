package com.promovatestproject.features.home.data.remote.source

import com.example.common.response.Response
import com.promovatestproject.features.home.data.remote.api.HomeApi
import com.promovatestproject.features.home.models.remote.HomeWrapperRemoteModel
import com.promovatestproject.network.utils.executeRequest
import javax.inject.Inject

internal class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRemoteDataSource {
    override suspend fun getHome(
        id: String,
        type: String,
        page: Int,
        pageSize: Int
    ): Response<HomeWrapperRemoteModel> =
        executeRequest {
            homeApi.getHome(id, type, page, pageSize)
        }
}