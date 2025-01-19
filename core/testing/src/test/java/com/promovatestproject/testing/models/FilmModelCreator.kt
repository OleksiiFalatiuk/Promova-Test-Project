package com.promovatestproject.testing.models

import com.promovatestproject.features.home.models.domain.FilmDomainModel
import com.promovatestproject.testing.utils.creators.MultipleModelsCreator

object FilmModelCreator : MultipleModelsCreator<FilmDomainModel> {
    override val model: FilmDomainModel = FilmDomainModel(
        id = 1,
        title = "Test Film",
        posterPath = null,
        voteAverage = 8.6,
        releaseDate = "2024-10-16",
        overview = "Test overview test overview"
    )

    override fun list(count: Int): List<FilmDomainModel> = List(count) { index ->
        model.copy(id = index)
    }
}