package com.example.features.movies.data.datasource.home

import com.example.movies.network.createHttpClient
import com.example.movies.network.response.details.DetailsItemResponse
import com.example.movies.network.response.details.TvDetails
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
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

    override suspend fun getMovieById(id: Int): Result<DetailsItemResponse> {
      try {
          val request = createHttpClient().get("movie/$id"){
              parameter("append_to_response", "videos")
              parameter("include_video_language", "en,null")

          }

          if (request.status.isSuccess()){
              val response = request.body<DetailsItemResponse>()
              return Result.success(response)
          }else{
              return Result.failure(Throwable(request.body<Throwable>().message))
          }


      }catch (e : Throwable){
          return Result.failure(e)

      }
    }

    override suspend fun getTvById(id: Int): Result<TvDetails> {
        try {
            val request = createHttpClient().get("tv/$id"){
                parameter("append_to_response", "videos")
                parameter("include_video_language", "en,null")

            }

            if (request.status.isSuccess()){
                val response = request.body<TvDetails>()
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