package com.example.movies.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaResponse(
    val page: Int? = null,

    @SerialName("total_pages")
    val totalPages: Int? = null,

    val results: List<MediaItem>? = null,

    @SerialName("total_results")
    val totalResults: Int? = null
)