package com.example.auth.ui

import com.example.auth.network.response.RequestTokenResponse
import com.example.movies.ui.main.Resources

data class AuthStates (
    val createRequest : Resources<RequestTokenResponse> = Resources.idle
)

sealed class AuthEvents{
    object  OnCreateRequest : AuthEvents()
}
