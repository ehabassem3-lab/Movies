package com.example.auth.ui

import com.example.auth.network.response.AccountResponse
import com.example.auth.network.response.RequestTokenResponse
import com.example.auth.network.response.SessionResponse
import com.example.movies.ui.main.Resources

data class AuthStates (
    val createRequest : Resources<RequestTokenResponse> = Resources.idle ,
    val createSession : Resources<SessionResponse> = Resources.idle ,
    val  getAccount  : Resources<AccountResponse> = Resources.idle ,
    val uiState: AuthUiState = AuthUiState.Idle

)

sealed interface AuthUiState {

    data object Idle : AuthUiState

    data object WaitingForApproval : AuthUiState

    data object CreatingSession : AuthUiState

    data object LoadingAccount : AuthUiState

    data object Success : AuthUiState

    data class Error(
        val message: String
    ) : AuthUiState
}

sealed class AuthEvents{
    object  OnCreateRequest : AuthEvents()
    data class  OnCreateSession( val requestToken: String) : AuthEvents()
    data  class  OnGetAccount( val sessionId: String) : AuthEvents()
}
