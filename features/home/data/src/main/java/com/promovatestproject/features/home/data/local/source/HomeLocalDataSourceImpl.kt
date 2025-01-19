package com.promovatestproject.features.home.data.local.source

import com.promovatestproject.features.home.data.local.dao.FilmDao
import com.promovatestproject.features.models.local.FilmRoomModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val dao: FilmDao
) : HomeLocalDataSource {
    override fun getAllFlow(): Flow<List<FilmRoomModel>> = dao.getAllFlow()

    override suspend fun insertFilm(film: FilmRoomModel) {
        dao.upsert(film)
    }

    override suspend fun deleteTour(film: FilmRoomModel) {
        dao.delete(film)
    }
}