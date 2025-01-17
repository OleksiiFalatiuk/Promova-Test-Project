package com.promovatestproject.features.home.models.remote

import com.promovatestproject.features.home.models.domain.HomeDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: This code is generated from a template. You need to add your implementation
@Serializable
data class HomeRemoteModel(
    @SerialName("id")
    val id: String
)

fun HomeRemoteModel.toDomainModel() = HomeDomainModel(
    id = id
)