package com.example.movies.network.response.cast

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity("person")
data class Actor(
	val id: Int? = null,

	val name: String? = null,

	val biography: String? = null,

	val birthday: String? = null,

	val deathday: String? = null,

	val gender: Int? = null,

	val popularity: Double? = null,

	@SerialName("also_known_as")
	val alsoKnownAs: List<String>? = null,

	@SerialName("imdb_id")
	val imdbId: String? = null,

	@SerialName("known_for_department")
	val knownForDepartment: String? = null,

	@SerialName("profile_path")
	val profilePath: String? = null,

	@SerialName("place_of_birth")
	val placeOfBirth: String? = null,

	val homepage: String? = null
)
