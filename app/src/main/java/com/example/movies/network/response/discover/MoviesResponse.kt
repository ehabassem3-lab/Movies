package com.example.movies.network.response.discover

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse
    (
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<MoviesItem?>? = null,
    val totalResults: Int? = null
            )

