package com.example.features.movies.network.response.details

import kotlinx.serialization.Serializable

@Serializable

data class SpokenLanguagesItem(
	val name: String? = null,
	val iso6391: String? = null,
	val englishName: String? = null
)
