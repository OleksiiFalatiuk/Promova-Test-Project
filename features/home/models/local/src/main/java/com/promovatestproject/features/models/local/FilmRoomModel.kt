package com.promovatestproject.features.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.promovatestproject.features.home.models.domain.FilmDomainModel

@Entity(
    tableName = "FILMS",
    primaryKeys = ["FILM_ID"]
)
data class FilmRoomModel(
    @ColumnInfo(name = "FILM_ID")
    val id: Int,
    @ColumnInfo(name = "FILM_TITLE")
    val title: String,
    @ColumnInfo(name = "FILM_VOTE_AVERAGE")
    val voteAverage: Double?,
    @ColumnInfo(name = "FILM_RELEASE_DATE")
    val releaseDate: String,
    @ColumnInfo(name = "FILM_OVERVIEW")
    val overview: String
)

fun FilmRoomModel.toDomainModel(posterPath: String?) = FilmDomainModel(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    overview = overview,
    releaseDate = releaseDate
)

fun FilmDomainModel.toLocalModel() = FilmRoomModel(
    id = id,
    title = title,
    voteAverage = voteAverage,
    overview = overview,
    releaseDate = releaseDate
)


