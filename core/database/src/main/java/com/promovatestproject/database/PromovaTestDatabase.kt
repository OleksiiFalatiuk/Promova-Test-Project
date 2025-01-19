package com.promovatestproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.promovatestproject.features.home.data.local.dao.FilmDao
import com.promovatestproject.features.models.local.FilmRoomModel

@Database(
    entities = [FilmRoomModel::class],
    version = 1
)
abstract class PromovaTestDatabase : RoomDatabase() {
    abstract fun getFilmDao(): FilmDao
}