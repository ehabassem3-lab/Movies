package com.example.movies.network.response.details

import kotlinx.serialization.Serializable

@Serializable
data class DetailsItem(
	val rated: Boolean? = null,
	val id: Int? = null,
	val favorite: Boolean? = null,
	val watchlist: Boolean? = null
)
