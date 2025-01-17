package com.promovatestproject.features.home.data.remote.source

import com.example.common.response.Response
import com.promovatestproject.features.home.models.remote.HomeWrapperRemoteModel

internal interface HomeRemoteDataSource {
    suspend fun getHome(
        id: String,
        type: String,
        page: Int,
        pageSize: Int
    ): Response<HomeWrapperRemoteModel>
}