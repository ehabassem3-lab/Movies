package com.example.movies.network.response.cast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class CastItem(
	val character: String? = null,
	val name: String? = null,
	@SerialName("profile_path")
	val profilePath: String? = null,
	val id: Int? = null,
	val order: Int? = null
)
