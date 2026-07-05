package com.example.features.movies.network.response.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    @SerialName("results")
    val results: List<VideoItem> = emptyList()
)

@Serializable
data class VideoItem(

    @SerialName("key")
    val key: String,

    @SerialName("name")
    val name: String,

    @SerialName("site")
    val site: String,

    @SerialName("type")
    val type: String,

    @SerialName("official")
    val official: Boolean = false
)