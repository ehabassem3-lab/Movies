package com.example.movies.domain.repositories.home

import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.details.DetailsItemResponse
import com.example.movies.network.response.details.TvDetails
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.network.response.search.SearchResponse

interface HomeRepository {

    suspend fun getDiscoveryTv(page : Int? , genre : Int? = null) : Result<DiscoverResponse?>
    suspend fun getDiscoveryMovies(page : Int? , genre : Int? = null) : Result<MoviesResponse?>
    suspend fun addToFavorite(mediaId: Int, mediaType: String, favorite: Boolean): Result<Unit>

    suspend fun getMovieById(id : Int ) : Result<MoviesItem>
    suspend fun getTvById(id : Int ) : Result<DiscoverItem>
    suspend fun getMovieCrew(id : Int) : Result<Cast>
    suspend fun getTvCrew(id : Int) : Result<Cast>
    suspend fun  getActor(id : Int) : Result<Actor>
    suspend fun getActorWork(id : Int) : Result<ActorWork>
}