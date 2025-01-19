package com.promovatestproject.features.home.domain.repository

import androidx.paging.PagingData
import com.promovatestproject.features.home.models.domain.FilmDomainModel
import com.promovatestproject.features.home.models.domain.HomeDomainModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getFilms(): Flow<PagingData<HomeDomainModel>>
    fun getLikedFilmsFlow(): Flow<List<HomeDomainModel>>
    suspend fun insertLikedFilm(film: FilmDomainModel)
    suspend fun deleteLikedFilm(film: FilmDomainModel)
}