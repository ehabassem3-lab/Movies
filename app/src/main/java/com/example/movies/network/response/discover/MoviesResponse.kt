package com.example.movies.network.response.discover

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "Movies" )
data class MoviesResponse
    (
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<MoviesItem?>? = null,
    val totalResults: Int? = null
            )

