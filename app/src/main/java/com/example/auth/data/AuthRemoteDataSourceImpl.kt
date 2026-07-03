package com.example.auth.data

import com.example.auth.network.response.AccountResponse
import com.example.auth.network.response.RequestTokenResponse
import com.example.auth.network.response.SessionRequestBody
import com.example.auth.network.response.SessionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess
import javax.inject.Inject
import kotlin.jvm.Throws

class AuthRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : AuthRemoteDataSource {

    override suspend fun createRequestToken(): Result<RequestTokenResponse >{
        try {
             val request =client.get("authentication/token/new")
             if (request.status.isSuccess()){
                 val response = request.body<RequestTokenResponse>()
                 return Result.success(response)
             }else{
                 return Result.failure(Throwable(request.status.description))
             }

        }catch (e : Throwable){
            return Result.failure(e)
        }

    }

    override suspend fun createSession(
        requestToken: String
    ): Result<SessionResponse >{

        return client.post("authentication/session/new") {
            setBody(
                mapOf(
                    "request_token" to requestToken
                )
            )
        }.body()
    }

    override suspend fun getAccount(
        sessionId: String
    ): Result<AccountResponse> {

        return client.get("account") {
            parameter("session_id", sessionId)
        }.body()
    }
}