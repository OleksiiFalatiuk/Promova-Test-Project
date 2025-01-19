package com.promovatestproject.features.home.models.presentation

import com.promovatestproject.features.home.models.domain.HomeDomainModel

data class HomePresentationModel(
    val releaseDate: String,
    val films: List<FilmPresentationModel>
)

fun HomeDomainModel.toPresentationModel() = HomePresentationModel(
    releaseDate = releaseDate,
    films = films.map { it.toPresentationModel() }
)