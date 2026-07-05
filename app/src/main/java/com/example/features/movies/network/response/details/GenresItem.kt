package com.example.features.movies.network.response.details

import kotlinx.serialization.Serializable

@Serializable

data class GenresItem(
	val name: String? = null,
	val id: Int? = null
)
