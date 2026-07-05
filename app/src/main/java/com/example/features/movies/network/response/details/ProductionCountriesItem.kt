package com.example.features.movies.network.response.details

import kotlinx.serialization.Serializable

@Serializable

data class ProductionCountriesItem(
	val iso31661: String? = null,
	val name: String? = null
)
