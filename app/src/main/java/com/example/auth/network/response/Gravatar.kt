package com.example.auth.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gravatar(

    @SerialName("hash")
    val hash: String? = null
)