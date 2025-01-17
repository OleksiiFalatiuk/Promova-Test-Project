package com.promovatestproject.features.home.models.presentation

import com.promovatestproject.features.home.models.domain.HomeDomainModel

// TODO: This code is generated from a template. You need to add your implementation
data class HomePresentationModel(
    val id: String
)

fun HomeDomainModel.toPresentationModel() = HomePresentationModel(
    id = id
)