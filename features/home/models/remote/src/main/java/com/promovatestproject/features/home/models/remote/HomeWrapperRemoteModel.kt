package com.promovatestproject.features.home.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: This code is generated from a template. You need to add your implementation
@Serializable
data class HomeWrapperRemoteModel(
    @SerialName("items")
    val items: List<HomeRemoteModel>
)