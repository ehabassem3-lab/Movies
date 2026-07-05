package com.example.features.auth.data

import com.example.auth.network.response.AccountResponse
import com.example.auth.network.response.RequestTokenResponse
import com.example.auth.network.response.SessionRequestBody
import com.example.auth.network.response.SessionResponse

interface AuthRemoteDataSource {
    suspend fun createRequestToken(): Result<RequestTokenResponse>

    suspend fun createSession(requestToken: String): Result< SessionResponse>

    suspend fun getAccount(sessionId: String): Result<AccountResponse>
}