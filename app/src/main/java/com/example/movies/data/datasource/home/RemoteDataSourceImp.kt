package com.example.movies.data.datasource.home

import com.example.movies.network.createHttpClient
import com.example.movies.network.response.discover.DiscoverResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import jakarta.inject.Inject

class RemoteDataSourceImp  @Inject constructor(
    private val client: HttpClient
): RemoteDataSource{
    override suspend fun getDiscover(): Result<DiscoverResponse> {
        try {
            val request = createHttpClient().get("discover/tv"){}
             if (request.status.isSuccess()){
                 val response = request.body<DiscoverResponse>()
                 return Result.success(response)
             }else{
                 return Result.failure(Throwable(request.body<Throwable>().message))
             }

        }catch (e : Throwable){
            return Result.failure(e)
        }

    }
}