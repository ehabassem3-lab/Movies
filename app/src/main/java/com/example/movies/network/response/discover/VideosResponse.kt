package com.example.movies.network.response.discover

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable

data class VideosResponse(
    val results: List<VideoItem> = emptyList()
)

@Serializable
data class VideoItem(
    val key: String? = null,
    val name: String? = null,
    val site: String? = null,
    val type: String? = null,
    val official: Boolean? = null,
    @SerialName("published_at")
    val publishedAt: String? = null
)