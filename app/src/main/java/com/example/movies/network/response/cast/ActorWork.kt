package com.example.movies.network.response.cast

import kotlinx.serialization.Serializable

@Serializable
data class ActorWork(
	val cast: List<ActorWorkItem?>? = null,
	val crew: List<ActorCrewItem?>? = null
)
