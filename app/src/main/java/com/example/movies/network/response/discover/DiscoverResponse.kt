package com.example.movies.network.response.discover

data class DiscoverResponse(
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<DiscoverItem?>? = null,
    val totalResults: Int? = null
)
