package com.example.auth.data.remote

import android.accounts.Account
import com.example.auth.network.response.AccountResponse
import com.example.auth.network.response.RequestTokenResponse
import com.example.auth.network.response.SessionResponse
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse

interface AuthRemoteDataSource {
    suspend fun createRequestToken(): Result<RequestTokenResponse>

    suspend fun createSession(requestToken: String): Result< SessionResponse>

    suspend fun getAccount(sessionId: String): Result<AccountResponse>
    suspend fun getFavouriteMovies(    sessionId: String, accountId: Int) : Result<MoviesResponse>
    suspend fun getFavouriteTv(   sessionId: String, accountId: Int) : Result<DiscoverResponse>

}