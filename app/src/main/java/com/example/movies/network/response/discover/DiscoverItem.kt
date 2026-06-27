package com.example.movies.network.response.discover
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class DiscoverItem(
	val firstAirDate: String? = null,
	val overview: String? = null,
	val originalLanguage: String? = null,
	val genreIds: List<Int?>? = null,
	val posterPath: String? = null,
	val originCountry: List<String?>? = null,
	val backdropPath: String? = null,
	val originalName: String? = null,
	val popularity: Double? = null,
	val voteAverage: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val voteCount: Int? = null
)
