package com.example.movies.network.response.discover

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesItem (
    val firstAirDate: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    val originCountry: List<String?>? = null,
    val backdropPath: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("title")
    val name: String? = null,
    val id: Int? = null,
    @SerialName("vote_count")
    val voteCount: Double? = null ,
    val type : String = "Movie"

)

