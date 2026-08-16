package com.example.movies.data.datasource.home

import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.cast.CastItem
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.google.gson.annotations.Until

interface LocalDataSource {



        suspend fun getMovies(
            page: Int,
            genre: Int?
        ): Result<MoviesResponse>

        suspend fun saveMovies(
            page: Int,
            genre: Int?,
            response: MoviesResponse
        ): Result<Unit>


    suspend fun getTv(
        page: Int,
        genre: Int?
    ): Result<DiscoverResponse>

    suspend fun saveTv(
        page: Int,
        genre: Int?,
        response: DiscoverResponse
    ): Result<Unit>

    suspend fun getMovie(id : Int) : Result<MoviesItem>
    suspend fun getTv(id : Int) : Result<DiscoverItem>
    suspend fun getCast() : Result<Cast>
    suspend fun saveCast (cast : List<CastItem>) : Unit
    suspend fun savePerson( actor : Actor ) : Result<Unit>
    suspend fun savePersonWork( actor : ActorWork ) : Result<Unit>
    suspend fun  getPerson(id : Int) : Result<Actor>
    suspend fun  getPersonWork(id :Int) : Result<ActorWork>

}