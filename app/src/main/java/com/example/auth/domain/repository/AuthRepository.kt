package com.example.auth.domain.repository

import com.example.auth.network.response.RequestTokenResponse

interface AuthRepository {
    suspend fun createRequestToken(): Result<RequestTokenResponse>


}