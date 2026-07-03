package com.example.auth.data.repository

import com.example.auth.data.AuthRemoteDataSource
import com.example.auth.domain.repository.AuthRepository
import com.example.auth.network.response.RequestTokenResponse
import com.example.movies.data.datasource.home.RemoteDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthRemoteDataSource
)  : AuthRepository{
    override suspend fun createRequestToken(): Result<RequestTokenResponse >{
     val request = dataSource.createRequestToken()
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }
}