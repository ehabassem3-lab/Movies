package com.example.features.auth.network.response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionRequestBody(

    @SerialName("request_token")
    val requestToken: String
)