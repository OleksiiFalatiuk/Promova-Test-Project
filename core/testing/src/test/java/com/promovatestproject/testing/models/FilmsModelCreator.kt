package com.promovatestproject.testing.models

import com.promovatestproject.features.home.models.domain.HomeDomainModel
import com.promovatestproject.testing.utils.creators.MultipleModelsCreator

object FilmsModelCreator : MultipleModelsCreator<HomeDomainModel> {
    override val model: HomeDomainModel = HomeDomainModel(
        releaseDate = "2024-10-16",
        films = FilmModelCreator.list(4)
    )

    override fun list(count: Int): List<HomeDomainModel> = List(count) {
        model
    }
}