package com.promovatestproject.features.home.models.remote

import com.promovatestproject.features.home.models.domain.FilmDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeRemoteModel(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("overview")
    val overview: String
)

fun HomeRemoteModel.toDomainModel() = FilmDomainModel(
    id = id,
    title = title,
    posterPath = "https://image.tmdb.org/t/p/w200$posterPath",
    voteAverage = voteAverage,
    overview = overview,
    releaseDate = releaseDate
)