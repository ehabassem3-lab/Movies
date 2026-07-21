package com.example.movies.data.datasource.home

import com.example.movies.network.createHttpClient
import com.example.movies.network.request.FavoriteRequest
import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.details.DetailsItem
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
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
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

    override suspend fun getMovieById(id: Int): Result<MoviesItem> {
      try {
          val request = createHttpClient().get("movie/$id"){
              parameter("append_to_response", "videos")
              parameter("include_video_language", "en,null")

          }

          if (request.status.isSuccess()){
              val response = request.body<MoviesItem>()
              return Result.success(response)
          }else{
              return Result.failure(Throwable(request.body<Throwable>().message))
          }


      }catch (e : Throwable){
          return Result.failure(e)

      }
    }

    override suspend fun getTvById(id: Int): Result<DiscoverItem> {
        try {
            val request = createHttpClient().get("tv/$id"){
                parameter("append_to_response", "videos")
                parameter("include_video_language", "en,null")

            }

            if (request.status.isSuccess()){
                val response = request.body<DiscoverItem>()
                return Result.success(response)
            }else{
                return Result.failure(Throwable(request.body<Throwable>().message))
            }


        }catch (e : Throwable){
            return Result.failure(e)

        }
    }

    override suspend fun getTvCast(id: Int): Result<Cast> {
        return try {
            val request = client.get("tv/${id}/credits")
            if (request.status.isSuccess()){
                Result.success( request.body<Cast>())
            }else{
                Result.failure(Throwable(request.status.description))
            }

        }catch (e : Throwable){
            Result.failure(e)
        }
    }

    override suspend fun getMovieCast(id: Int): Result<Cast> {
        return try {
            val request = client.get("movie/${id}/credits")
            if (request.status.isSuccess()){
                Result.success( request.body<Cast>())
            }else{
                Result.failure(Throwable(request.status.description))
            }

        }catch (e : Throwable){
            Result.failure(e)
        }
    }

    override suspend fun getPerson(id: Int): Result<Actor> {
        return   try {
            val request = client.get("person/${id}")
            if (request.status.isSuccess()){
                Result.success(request.body())
            }else{
                Result.failure(Throwable(request.status.description))
            }

        }catch (e : Throwable){
            Result.failure(e)
        }
    }

    override suspend fun getPersonWork(id: Int): Result<ActorWork> {

        return try {
            val request = client.get("person/${id}/combined_credits")
            if (request.status.isSuccess()){
                Result.success(request.body())
            }else{
                Result.failure(Throwable(request.status.description))
            }

        }catch (e : Throwable){
            return Result.failure(e)

        }
    }

    override suspend fun addToFavorite(
        accountId: Int,
        sessionId: String,
        mediaId: Int,
        mediaType: String,
        favorite: Boolean
    ): Result<Unit> {
         return try {
             val request = createHttpClient().post ("account/${accountId}/favorite"){
                 parameter("session_id", sessionId)
                 setBody(FavoriteRequest(mediaType,mediaId,favorite))
             }
             if (request.status.isSuccess()){
                 val response = request.body<Unit>()
                 Result.success(response)
             }else{
                 Result.failure(Throwable(request.status.description))
             }

        }catch (e : Throwable){
             Result.failure(e)
        }
    }


    override suspend fun searchMovies(search: String , page: Int?): Result<SearchResponse> {
        try {
            val request = createHttpClient().get("search/multi"){
                parameter("page" , page)
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

    override suspend fun getDetails(id: Int, mediaType: String , sessionId: String): Result<DetailsItem> {
     return   try {

            val request = if (mediaType == "Movie") client.get("movie/${id}/account_states") {
                parameter(
                    "session_id",
                    sessionId
                )
            }
            else client.get("tv/${id}/account_states") { parameter("session_id", sessionId) }


            if (request.status.isSuccess()) {
                Result.success(request.body<DetailsItem>())
            } else {
                Result.failure(Throwable(request.status.description))
            }

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}