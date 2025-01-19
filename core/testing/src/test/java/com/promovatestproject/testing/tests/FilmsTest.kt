package com.promovatestproject.testing.tests

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.promovatestproject.features.home.domain.repository.HomeRepository
import com.promovatestproject.features.home.models.domain.FilmDomainModel
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import com.promovatestproject.testing.models.FilmModelCreator
import com.promovatestproject.testing.repository.HomeRepositoryMockImpl
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class FilmsTest {
    private lateinit var homeRepository: HomeRepositoryMockImpl

    @Before
    fun setup() {
        homeRepository = HomeRepositoryMockImpl()
    }

    @Test
    fun `getFilms returns correct PagingData with expected elements`() = runTest {
        val pagingData = homeRepository.getFilms().asSnapshot()
        assertEquals(HomeRepositoryMockImpl.FILMS_NUMBER, pagingData.size)
    }

    @Test
    fun `getFilms returns correct film models in PagingData`() = runTest {
        // Act
        val pagingData = homeRepository.getFilms().asSnapshot()

        // Assert
        pagingData.forEach { page ->
            page.films.forEach { film ->
                assertEquals(FilmModelCreator.model.title, film.title)
                assertEquals(FilmModelCreator.model.overview, film.overview)
                assertEquals(
                    FilmModelCreator.model.voteAverage ?: 0.0,
                    film.voteAverage ?: 0.0,
                    0.1
                )
            }
        }
    }

    @Test
    fun `getFilms does not return films with null important fields`() = runTest {
        // Act
        val pagingData = homeRepository.getFilms().asSnapshot()

        // Assert
        pagingData.forEach { page ->
            page.films.forEach { film ->
                assertNotNull(film.title)
                assertNotNull(film.overview)
            }
        }
    }

    @Test
    fun `getFilms returns films with unique ids within a page`() = runTest {
        // Act
        val pagingData = homeRepository.getFilms().asSnapshot()

        // Assert
        pagingData.forEach { page ->
            val ids = page.films.map { it.id }.toSet()
            assertEquals(page.films.size, ids.size)
        }
    }

    @Test
    fun `getFilms returns PagingData of HomeDomainModel`() = runTest {
        // Act
        val result = homeRepository.getFilms()

        // Assert
        assertTrue(result is Flow<PagingData<HomeDomainModel>>)
    }

    @Test
    fun `getFilms handles empty pages`() = runTest {
        val emptyRepository = object : HomeRepository {
            override fun getFilms(): Flow<PagingData<HomeDomainModel>> = flowOf(PagingData.empty())
            override fun getLikedFilmsFlow(): Flow<List<HomeDomainModel>> = flowOf()
            override suspend fun insertLikedFilm(film: FilmDomainModel) {}
            override suspend fun deleteLikedFilm(film: FilmDomainModel) {}
        }

        // Act
        val pagingData = emptyRepository.getFilms().asSnapshot()

        // Assert
        assertTrue(pagingData.isEmpty())
    }
}