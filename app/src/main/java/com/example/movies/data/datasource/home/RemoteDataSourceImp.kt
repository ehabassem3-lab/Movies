package com.example.movies.data.datasource.home

import com.example.movies.network.createHttpClient
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.network.response.search.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import io.ktor.http.parameters
import jakarta.inject.Inject

class RemoteDataSourceImp  @Inject constructor(
    private val client: HttpClient
): RemoteDataSource{
    override suspend fun getDiscover(page: Int?, genre: Int?): Result<DiscoverResponse> {
        try {
            val request = createHttpClient().get("discover/tv"){
               parameter("page" , page)
                  parameter("with_genres",genre)
            }

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

    override suspend fun getDiscoverMovies(
        page: Int?,
        genre: Int?
    ): Result<MoviesResponse> {
        try {

            val request = createHttpClient().get("discover/movie"){
                parameter("page" , page)
                parameter("with_genres",genre)
            }

            if (request.status.isSuccess()){
                val response = request.body<MoviesResponse>()
                return Result.success(response)
            }else{
                return Result.failure(Throwable(request.body<Throwable>().message))
            }

        }catch (e : Throwable){
             return Result.failure(e)
        }
    }

    override suspend fun searchMovies(search: String): Result<SearchResponse> {
        try {
            val request = createHttpClient().get("search/movie"){
                parameter("query" , search)

            }

            if (request.status.isSuccess()){
                val response = request.body<SearchResponse>()
                return Result.success(response)
            }else{
                return Result.failure(Throwable(request.body<Throwable>().message))
            }

        }catch (e : Throwable){
            return Result.failure(e)
        }
    }
}