package com.promovatestproject.features.home.models.remote

import com.promovatestproject.features.home.models.domain.HomeDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeWrapperRemoteModel(
    @SerialName("results")
    val items: List<HomeRemoteModel>
)

fun HomeWrapperRemoteModel.toHomeDomainModel(): List<HomeDomainModel> {
    return items
        .groupBy { it.releaseDate }
        .map { (releaseDate, films) ->
            HomeDomainModel(
                releaseDate = releaseDate,
                films = films.map { it.toDomainModel() }
            )
        }
}