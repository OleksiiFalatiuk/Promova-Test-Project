package com.promovatestproject.features.home.data.remote.api

import com.promovatestproject.features.home.models.remote.HomeWrapperRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface HomeApi {
    @GET("/api/v1/your_url")
    suspend fun getHome(
        id: String,
        type: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<HomeWrapperRemoteModel>
}