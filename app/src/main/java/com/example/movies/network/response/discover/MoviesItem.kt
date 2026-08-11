package com.example.movies.network.response.discover

import com.example.movies.network.response.Genres
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class Genre(
    val id: Int? = null,
    val name: String? = null
)
@Serializable
data class MoviesItem (

    val firstAirDate: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    val genres: List<Genre>? = null,
    val originCountry: List<String?>? = null,
    val backdropPath: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("title")
    val name: String? = null,
    @SerialName("videos")
    val videos: VideosResponse? = null,
    val id: Int? = null,

    @SerialName("vote_count")
    val voteCount: Double? = null,

    val type : String = "Movie",
    val     fav  : Boolean? = false,
    val isRated : Boolean? = false,
    var rate : Double? = .5 ,
    val page: Int? = 0 ,
    val genreId: Int? = 0

)

