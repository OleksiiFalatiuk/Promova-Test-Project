package com.promovatestproject.testing.repository

import androidx.paging.PagingData
import com.promovatestproject.features.home.domain.repository.HomeRepository
import com.promovatestproject.features.home.models.domain.FilmDomainModel
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import com.promovatestproject.testing.models.FilmsModelCreator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeRepositoryMockImpl : HomeRepository {
    override fun getFilms(): Flow<PagingData<HomeDomainModel>> = FilmsModelCreator.paging(FILMS_NUMBER)

    override fun getLikedFilmsFlow(): Flow<List<HomeDomainModel>> = flowOf()

    override suspend fun insertLikedFilm(film: FilmDomainModel) {}

    override suspend fun deleteLikedFilm(film: FilmDomainModel) {}

    companion object {
        const val FILMS_NUMBER = 5
    }
}