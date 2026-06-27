package com.example.movies.network.response.search
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultsItem?>? = null,
	val totalResults: Int? = null
)
