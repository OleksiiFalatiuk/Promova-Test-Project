package com.promovatestproject.features.home.data.remote.source

import com.example.common.response.Response
import com.promovatestproject.features.home.models.remote.HomeWrapperRemoteModel

interface HomeRemoteDataSource {
    suspend fun getFilms(page: Int): Response<HomeWrapperRemoteModel>
}