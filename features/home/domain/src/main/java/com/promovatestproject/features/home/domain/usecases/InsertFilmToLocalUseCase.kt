package com.promovatestproject.features.home.domain.usecases

import com.promovatestproject.features.home.domain.repository.HomeRepository
import com.promovatestproject.features.home.models.domain.FilmDomainModel
import javax.inject.Inject

class InsertFilmToLocalUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(film: FilmDomainModel) = homeRepository.insertLikedFilm(film = film)
}