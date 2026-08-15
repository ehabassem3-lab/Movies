package com.example.movies.network.response.cast

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Entity(tableName = "cast")
@Serializable
data class CastItem(
    val character: String? = null,
    val name: String? = null,
    @SerialName("profile_path")
    val profilePath: String? = null,
    val id: Int? = null,
    val order: Int? = null
)