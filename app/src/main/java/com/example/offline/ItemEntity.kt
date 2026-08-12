package com.example.offline

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.Resources
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Entity(
    tableName = "movies",
    primaryKeys = ["id", "page", "genreId"]
)
data class MovieEntity(

    val id: Int,

    val name: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,

    val voteAverage: Double?,
    val voteCount: Double?,

    val firstAirDate: String?,
    val originalLanguage: String?,
    val originalName: String?,
    val popularity: Double?,

    val type: String,
    val fav: Boolean?,
    val isRated: Boolean?,
    val rate: Double?,

    val page: Int,
    val genreId: Int
)
@Entity(
    tableName = "tv",
    primaryKeys = ["id", "page", "genreId"]
)
data class TvEntity(
    val id: Int,

    val name: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,

    val voteAverage: Double?,
    val voteCount: Double?,

    val firstAirDate: String?,
    val originalLanguage: String?,
    val originalName: String?,
    val popularity: Double?,

    val type: String,
    val fav: Boolean?,
    val isRated: Boolean?,
    val rate: Double?,

    val page: Int,
    val genreId: Int
)


