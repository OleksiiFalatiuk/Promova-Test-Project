package com.promovatestproject.features.home.models.domain

data class FilmDomainModel(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val voteAverage: Double?,
    val releaseDate: String,
    val overview: String
)