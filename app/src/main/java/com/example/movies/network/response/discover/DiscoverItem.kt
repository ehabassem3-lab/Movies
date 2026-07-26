package com.example.movies.network.response.discover
import com.example.movies.network.response.Genres
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class DiscoverItem(
	val firstAirDate: String? = null,
	val overview: String? = null,
	val originalLanguage: String? = null,
	@SerialName("genre_ids")
	val genreIds: List<Int>? = null,
	@SerialName("poster_path")
	val posterPath: String? = null,
	val genres: List<Genre>? = null ,
	@SerialName("origin_country")
	val originCountry: List<String?>? = null,
	val backdropPath: String? = null,
	@SerialName("original_name")
	val originalName: String? = null,
	val popularity: Double? = null,
	@SerialName("vote_average")
	val voteAverage: Double? = null,
	val name: String? = null,
	val id: Int? = null,
	@SerialName("vote_count")
	val voteCount: Double? = null ,
	val type : String = "TV" ,
	val fav : Boolean? = false,
	@SerialName("videos")
	val videos: VideosResponse? = null
)
