package com.example.features.auth.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Avatar(

    @SerialName("gravatar")
    val gravatar: Gravatar? = null,

    @SerialName("tmdb")
    val tmdb: TmdbAvatar? = null
)