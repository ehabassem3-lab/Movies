package com.example.features.auth.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(

    @SerialName("avatar")
    val avatar: Avatar? = null,

    @SerialName("id")
    val id: Int,

    @SerialName("iso_639_1")
    val iso6391: String? = null,

    @SerialName("iso_3166_1")
    val iso31661: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("include_adult")
    val includeAdult: Boolean? = null,

    @SerialName("username")
    val username: String
)