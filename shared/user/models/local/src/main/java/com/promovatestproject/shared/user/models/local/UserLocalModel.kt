package com.promovatestproject.shared.user.models.local

import com.example.shared.user.models.domain.UserDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class UserLocalModel(
    val accessKey: String,
    val email: String,
    val userName: String?,
    val profileImageUrl: String?
)

fun UserDomainModel.toLocalModel() = UserLocalModel(
    accessKey = accessKey,
    email = email,
    userName = userName,
    profileImageUrl = profileImageUrl
)

fun UserLocalModel.toDomainModel() = UserDomainModel(
    accessKey = accessKey,
    email = email,
    userName = userName,
    profileImageUrl = profileImageUrl
)
