package com.promovatestproject.features.home.models.domain

data class HomeDomainModel(
    val releaseDate: String,
    val films: List<FilmDomainModel>
)
