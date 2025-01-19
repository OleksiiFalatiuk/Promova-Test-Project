package com.promovatestproject.features.home.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.common.basedao.BaseDao
import com.promovatestproject.features.models.local.FilmRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao : BaseDao<FilmRoomModel> {
    @Query("SELECT * FROM FILMS")
    fun getAllFlow(): Flow<List<FilmRoomModel>>

    @Query("SELECT * FROM FILMS")
    suspend fun getAll(): List<FilmRoomModel>

    @Transaction
    @Query("SELECT * FROM FILMS WHERE FILM_ID = :filmId")
    suspend fun getFilm(filmId: Int): FilmRoomModel?

    @Transaction
    suspend fun insertFilm(film: FilmRoomModel) {
        insertOrUpdate(film)
    }

    @Transaction
    suspend fun deleteTour(film: FilmRoomModel) {
        delete(film)
    }
}