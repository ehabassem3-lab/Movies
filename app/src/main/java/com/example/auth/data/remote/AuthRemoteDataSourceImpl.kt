package com.example.auth.data.remote

import com.example.auth.network.response.AccountResponse
import com.example.auth.network.response.RequestTokenResponse
import com.example.auth.network.response.SessionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess
import javax.inject.Inject

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
       return try {
           val  request  =  client.post("authentication/session/new") { setBody(mapOf("request_token" to requestToken)) }
             if (request.status.isSuccess()) {
                 Result.success(request.body())
             }else{
                 Result.failure(Throwable(request.status.description))
             }
        }catch (e : Throwable){
           Result.failure(e)
        }

    }

    override suspend fun getAccount(
        sessionId: String
    ): Result<AccountResponse> {
           return  try {
              val request = client.get("account") { parameter("session_id", sessionId) }
                if (request.status.isSuccess()){
                    Result.success(request.body())
                }else{
                    Result.failure(Throwable(request.status.description))
                }
          }catch (e : Throwable){
               Result.failure(e)

          }

    }
}