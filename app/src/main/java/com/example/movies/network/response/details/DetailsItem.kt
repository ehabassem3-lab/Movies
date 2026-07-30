package com.example.movies.network.response.details

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class DetailsItem(
	val rated: JsonElement? = null,
	val id: Int? = null,
	val favorite: Boolean? = null,
	val watchlist: Boolean? = null
)
