package com.example.movies.data.datasource.home

import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.search.SearchResponse

interface RemoteDataSource{
   suspend fun getDiscover() : Result<DiscoverResponse>
   suspend fun searchMovies(search : String ) : Result<SearchResponse>
}