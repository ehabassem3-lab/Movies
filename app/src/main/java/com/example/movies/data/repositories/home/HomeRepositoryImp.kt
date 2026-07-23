package com.example.movies.data.repositories.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.auth.ds.PreferencesKeys
import com.example.movies.data.datasource.home.LocalDataSource
import com.example.movies.data.datasource.home.RemoteDataSource
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.network.createHttpClient
import com.example.movies.network.response.FavItem
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
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class HomeRepositoryImp  @Inject constructor(
    private val dataSource: RemoteDataSource ,
    private val local : LocalDataSource ,
    private  val dataStore : DataStore<Preferences>,
) : HomeRepository {


    override suspend fun getDiscoveryTv(page: Int?, genre: Int?): Result<DiscoverResponse?> {
      val request = dataSource.getDiscover(page,genre)
        return if (request.isSuccess){
            Result.success( request.getOrNull())
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getDiscoveryMovies(
        page: Int?,
        genre: Int?
    ): Result<MoviesResponse?> {
        val request = dataSource.getDiscoverMovies(page,genre)
        return if (request.isSuccess){
            Result.success( request.getOrNull())
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun addToFavorite(
        mediaId: Int,
        mediaType: String,
        favorite: Boolean
    ): Result<Unit> {
        val preferences = dataStore.data.first()

        val accountId = preferences[PreferencesKeys.ACCOUNT_ID]
            ?: return Result.failure(Exception("Account ID not found"))

        val sessionId = preferences[PreferencesKeys.SESSION_ID]
            ?: return Result.failure(Exception("Session ID not found"))

        val request = dataSource.addToFavorite(
            accountId = accountId,
            sessionId = sessionId,
            mediaId = mediaId,
            mediaType = mediaType,
            favorite = favorite
        )
         return if (request.isSuccess){
             Result.success(request.getOrNull()!!)
        }else{
             Result.failure(Throwable(request.exceptionOrNull()))
         }

    }

    override suspend fun addToWatchList(
        mediaId: Int,
        mediaType: String,
        watchList: Boolean
    ): Result<Unit> {
        val preferences = dataStore.data.first()
        val request = dataSource.addToWatchList(
            accountId = preferences[PreferencesKeys.ACCOUNT_ID]?:0,
            sessionId = preferences[PreferencesKeys.SESSION_ID]?:"",
            mediaId = mediaId,
            mediaType = mediaType,
            watchList = watchList
        )
        return  if (request.isSuccess){
                Result.success(request.getOrNull()!!)
        }else{
             Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getMovieById(id: Int): Result<MoviesItem> {
        val request = dataSource.getMovieById(id)
        return if (request.isSuccess){
            Result.success( request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getTvById(id: Int): Result<DiscoverItem> {
        val request = dataSource.getTvById(id)
        return if (request.isSuccess){
            Result.success( request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getMovieCrew(id: Int): Result<Cast> {
      val request = dataSource.getMovieCast(id)
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }

    }

    override suspend fun getTvCrew(id: Int): Result<Cast> {
        val request = dataSource.getTvCast(id)
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }
    override suspend fun getWatchListMovie(
    ): Result<MoviesResponse> {
        val preferences = dataStore.data.first()
      val request = dataSource.getUserWatchListMovies(
          accountId = preferences[PreferencesKeys.ACCOUNT_ID]?:0,
          sessionId = preferences[PreferencesKeys.SESSION_ID]?:""
      )
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }
    override suspend fun getWatchListTv(

    ): Result<DiscoverResponse> {
        val preferences = dataStore.data.first()
        val request = dataSource.getUserWatchListTv(
            accountId = preferences[PreferencesKeys.ACCOUNT_ID]?:0,
            sessionId = preferences[PreferencesKeys.SESSION_ID]?:""
        )
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }



    override suspend fun getActor(id: Int): Result<Actor> {
        val  request = dataSource.getPerson(id)
        return    if (request.isSuccess){
        Result.success(request.getOrNull()!!)
        }else{
        Result.failure(Throwable(request.exceptionOrNull()))
       }
       }

    override suspend fun getActorWork(id: Int): Result<ActorWork> {
        val request = dataSource.getPersonWork(id)
        return  if (request.isSuccess){
            Result.success(request.getOrNull()!!)

        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }

    }

    override suspend fun getDetails(id: Int, mediaType: String) : Result<DetailsItem>{
        val request = dataSource.getDetails(
            id = id,
            mediaType = mediaType,
            sessionId = dataStore.data.first()[PreferencesKeys.SESSION_ID]?:""
        )
           return    if (request.isSuccess){
               Result.success(request.getOrNull()!!)
        }else{
               Result.failure(Throwable(request.exceptionOrNull()))
           }
    }


}