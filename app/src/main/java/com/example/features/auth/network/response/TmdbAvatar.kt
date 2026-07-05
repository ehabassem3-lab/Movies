package com.example.features.auth.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbAvatar(

    @SerialName("avatar_path")
    val avatarPath: String? = null
)