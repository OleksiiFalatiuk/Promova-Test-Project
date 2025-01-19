package com.promovatestproject.features.home.data.local.source

import com.promovatestproject.features.models.local.FilmRoomModel
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
    fun getAllFlow(): Flow<List<FilmRoomModel>>
    suspend fun insertFilm(film: FilmRoomModel)
    suspend fun deleteTour(film: FilmRoomModel)
}