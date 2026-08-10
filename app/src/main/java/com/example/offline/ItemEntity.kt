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
    tableName = "items",
    primaryKeys = ["id", "type"]
)
data class ItemEntity(
    val id: Int,
    val type: String,
    val title: String,
    val posterPath: String?,
    val rating: Double
)
@Entity(tableName = "movie_sections")
data class MovieSectionEntity(
    @PrimaryKey
    val id: String,
    val genreId: Int?,
    val title: Int,
    val page: Int = 1
)

@Entity(tableName = "tv_sections")
data class TvSectionEntity(
    @PrimaryKey
    val id: String,
    val genreId: Int?,
    val title: Int,
    val page: Int = 1
)
@Entity(
    tableName = "movie_section_items",
    primaryKeys = ["sectionId", "movieId"]
)
data class MovieSectionItemEntity(
    val sectionId: String,
    val movieId: Int
)
@Entity(
    tableName = "tv_section_items",
    primaryKeys = ["sectionId", "tvId"]
)
data class TvSectionItemEntity(
    val sectionId: String,
    val tvId: Int
)