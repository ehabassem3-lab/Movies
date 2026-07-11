package com.example.movies.network.response.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsItem(

	val overview: String? = null,

	@SerialName("original_language")
	val originalLanguage: String? = null,

	@SerialName("original_title")
	val originalTitle: String? = null,

	val video: Boolean? = null,

	val name: String? = null,

	@SerialName("genre_ids")
	val genreIds: List<Int>? = null,

	@SerialName("poster_path")
	val posterPath: String? = null,

	@SerialName("backdrop_path")
	val backdropPath: String? = null,

	@SerialName("release_date")
	val releaseDate: String? = null,

	val popularity: Double? = null,
	@SerialName("media_type")
	val mediaType: String? = null,
	@SerialName("vote_average")
	val voteAverage: Double? = null,

	val id: Int? = null,

	val adult: Boolean? = null,

	@SerialName("vote_count")
	val voteCount: Int? = null
)
