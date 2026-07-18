package com.example.movies.network.response.cast

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Cast(
	val cast: List<CastItem?>? = null,
	val id: Int? = null,
	val crew: List<CrewItem?>? = null
)
