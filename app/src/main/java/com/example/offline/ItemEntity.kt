package com.example.offline

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.Resources
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
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

@Entity("person")
data class ActorEntity(
    @PrimaryKey
    val id: Int? = null,

    val name: String? = null,

    val biography: String? = null,

    val birthday: String? = null,

    val deathday: String? = null,

    val gender: Int? = null,

    val popularity: Double? = null,
    val imdbId: String? = null,

    val knownForDepartment: String? = null,

    val profilePath: String? = null,
    val placeOfBirth: String? = null,

    val homepage: String? = null
)

@Entity(tableName = "castItem")
data class CastEntity(
    val character: String? = null,
    val name: String? = null,
    val profilePath: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val order: Int? = null
)
