package com.example.movies.data.datasource.home

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

interface RemoteDataSource{
   suspend fun getDiscover(page : Int? = 1 , genre : Int? = null) : Result<DiscoverResponse>
   suspend fun getDiscoverMovies(page : Int? = 1 , genre : Int? = null) : Result<MoviesResponse>
   suspend fun getMovieById(id : Int ) : Result<MoviesItem>
   suspend fun getTvById(id : Int ) : Result<DiscoverItem>
suspend fun getTvCast(id : Int ) : Result<Cast>

suspend fun getMovieCast(id : Int ) : Result<Cast>
   suspend fun  getPerson(id : Int) : Result<Actor>
   suspend fun  getPersonWork(id :Int) : Result<ActorWork>
   suspend fun getUserRatedMovies(sessionId : String , accountId: Int )  : Result<MoviesResponse>

   suspend fun getUserRatedTv(sessionId : String , accountId: Int )  : Result<DiscoverResponse>
   suspend fun addToWatchList( accountId : Int , sessionId: String , mediaId: Int, mediaType: String, watchList: Boolean) : Result<Unit>

   suspend fun addToFavorite(accountId: Int, sessionId: String, mediaId: Int, mediaType: String, favorite: Boolean): Result<Unit>
   suspend fun getUserWatchListMovies(accountId : Int ,  sessionId : String) : Result<MoviesResponse>
   suspend fun getUserWatchListTv(accountId : Int ,  sessionId : String) : Result<DiscoverResponse>
   suspend fun searchMovies(search : String  , page : Int? = 1  ) : Result<SearchResponse>
   suspend fun getDetails(id : Int , mediaType: String ,  sessionId: String)  : Result<DetailsItem>
   suspend fun getMoviesTrending() : Result<MoviesResponse>
   suspend fun getTvTrending() : Result<DiscoverResponse>
   suspend fun rateTv(sessionId : String , rating : Double , movieId : Int , mediaType: String, rated: Boolean) : Result<Unit>
   suspend fun rateMovie(sessionId : String , rating : Double , movieId : Int , mediaType: String, rated: Boolean) : Result<Unit>


}