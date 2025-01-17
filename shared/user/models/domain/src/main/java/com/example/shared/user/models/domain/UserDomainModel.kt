package com.example.shared.user.models.domain

data class UserDomainModel(
    val accessKey: String,
    val email: String,
    val userName: String?,
    val profileImageUrl: String?
)