package com.example.movies.network.response.details

import kotlinx.serialization.Serializable

@Serializable

data class ProductionCompaniesItem(
	val logoPath: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val originCountry: String? = null
)
