package com.promovatesproject.shared.user.models.presentation

import com.example.shared.user.models.domain.UserDomainModel

data class UserPresentationModel(
    val accessKey: String,
    val email: String,
    val userName: String?,
    val profileImageUrl: String?
)

fun UserDomainModel.toPresentationModel() = UserPresentationModel(
    accessKey = accessKey,
    email = email,
    userName = userName,
    profileImageUrl = profileImageUrl
)