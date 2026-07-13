package com.example.auth.domain.repository

import com.example.auth.network.response.AccountResponse
import com.example.auth.network.response.RequestTokenResponse
import com.example.auth.network.response.SessionResponse
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.tabs.profile.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun createRequestToken(): Result<RequestTokenResponse>
    suspend fun createSession(requestToken: String): Result<SessionResponse>
    suspend fun getAccount(sessionId: String): Result<AccountResponse>
    suspend fun  LogOut () : Result<Unit>
    suspend fun isLoggedIn() : Result<Unit>
    suspend fun getUser() : Result<UserData>
    suspend fun getFavouriteMovies() : Result<MoviesResponse>
    suspend fun getFavouriteTv() : Result<DiscoverResponse>


}