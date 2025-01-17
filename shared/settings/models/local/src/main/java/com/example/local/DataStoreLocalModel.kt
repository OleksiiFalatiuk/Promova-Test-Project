package com.example.local

import com.promovatestproject.shared.user.models.local.UserLocalModel
import kotlinx.serialization.Serializable

@Serializable
data class DataStoreLocalModel(
    val user: UserLocalModel? = null
)
