package com.example.movies.network.response.cast

import kotlinx.serialization.Serializable

@Serializable

data class CrewItem(
	val name: String? = null,
	val id: Int? = null,
	val job: String? = null
)
