package com.promovatestproject.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.promovatestproject.database.PromovaTestDatabase
import com.promovatestproject.features.home.data.local.source.HomeLocalDataSourceImpl
import com.promovatestproject.features.models.local.FilmRoomModel
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DatabaseTests {
    private lateinit var context: Context

    private val film1 = FilmRoomModel(
        id = 1,
        title = "Test Title",
        voteAverage = 7.5,
        overview = "Test overview test overview",
        releaseDate = "2024-10-16",
    )

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    private fun createDb(): PromovaTestDatabase = Room.inMemoryDatabaseBuilder(
        context,
        PromovaTestDatabase::class.java
    ).build()

    @Test
    fun insertFilmTest() = runBlocking {
        val db = createDb()
        val filmsDao = db.getFilmDao()
        val filmsLocalDataSource = HomeLocalDataSourceImpl(filmsDao)

        filmsLocalDataSource.insertFilm(film1)

        TestCase.assertEquals(film1, filmsDao.getFilm(film1.id))

        db.close()
    }

    @Test
    fun allDatabaseTest() = runBlocking {
        val db = createDb()
        val filmsDao = db.getFilmDao()
        val filmsLocalDataSource = HomeLocalDataSourceImpl(filmsDao)

        filmsLocalDataSource.insertFilm(film1)
        filmsLocalDataSource.insertFilm(film1.copy(id = 2))
        filmsLocalDataSource.insertFilm(film1.copy(id = 3))

        TestCase.assertEquals(3, filmsDao.getAll().size)

        db.close()
    }
}