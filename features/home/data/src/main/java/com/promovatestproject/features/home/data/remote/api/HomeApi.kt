package com.promovatestproject.features.home.data.remote.api

import com.promovatestproject.features.home.models.remote.HomeWrapperRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("discover/movie")
    suspend fun getFilms(
        @Query("vote_count.gte") voteCount: Int,
        @Query("vote_average.gte") voteAverage: Int,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): Response<HomeWrapperRemoteModel>
}