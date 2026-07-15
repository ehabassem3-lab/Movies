package com.example.movies.network.response

import android.media.browse.MediaBrowser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaResponse(
    val page: Int? = null,

    @SerialName("total_pages")
    val totalPages: Int? = null,

//    val results: List<MediaBrowser.MediaItem>? = null,

    @SerialName("total_results")
    val totalResults: Int? = null
)