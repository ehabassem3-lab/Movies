package com.example.movies.data.datasource.home

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
   suspend fun getMovieById(id : Int ) : Result<DetailsItemResponse>
   suspend fun getTvById(id : Int ) : Result<TvDetails>


   suspend fun searchMovies(search : String  , page : Int? = 1  ) : Result<SearchResponse>
}