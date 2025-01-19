package com.promovatestproject.features.home.models.presentation

import com.promovatestproject.features.home.models.domain.FilmDomainModel

data class FilmPresentationModel(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val voteAverage: Double?,
    val releaseDate: String,
    val overview: String
)

fun FilmDomainModel.toPresentationModel() = FilmPresentationModel(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview
)

fun FilmPresentationModel.toDomainModel() = FilmDomainModel(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview
)
