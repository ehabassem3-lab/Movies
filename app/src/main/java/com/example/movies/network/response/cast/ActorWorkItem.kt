package com.example.movies.network.response.cast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorWorkItem(
	@SerialName("first_air_date")
	val firstAirDate: String? = null,
	val character: String? = null,
	@SerialName("media_type")
	val mediaType: String? = null,
	val name: String? = null,
	val id: Int? = null,
	@SerialName("release_date")
	val releaseDate: String? = null,
	val title: String? = null,
	@SerialName("poster_path")
	val posterPath: String? = null
)
