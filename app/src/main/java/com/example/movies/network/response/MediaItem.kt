package com.example.movies.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaItem(

    val id: Int? = null,

    val overview: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("genre_ids")
    val genreIds: List<Int>? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null,

    val popularity: Double? = null,

    @SerialName("original_language")
    val originalLanguage: String? = null,

    // Movies
    val title: String? = null,

    @SerialName("original_title")
    val originalTitle: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    // TV
    val name: String? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    val adult: Boolean? = null,

    val video: Boolean? = null,

    val mediaType: String? = null
)