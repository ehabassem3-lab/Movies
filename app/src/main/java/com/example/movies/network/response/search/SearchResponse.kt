package com.example.movies.network.response.search
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
	val page: Int? = null,

	@SerialName("total_pages")
	val totalPages: Int? = null,

	val results: List<ResultsItem>? = null,

	@SerialName("total_results")
	val totalResults: Int? = null
)
