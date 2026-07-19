package com.example.movies.network.response.cast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorCrewItem(
    val id: Int? = null,

    val title: String? = null,

    val name: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null
)