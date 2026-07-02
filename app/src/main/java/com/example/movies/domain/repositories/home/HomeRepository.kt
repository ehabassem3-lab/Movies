package com.example.movies.domain.repositories.home

import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.network.response.search.SearchResponse

interface HomeRepository {
    suspend fun getDiscoveryTv(page : Int? , genre : Int? = null) : Result<DiscoverResponse?>
    suspend fun getDiscoveryMovies(page : Int? , genre : Int? = null) : Result<MoviesResponse?>

    suspend fun getMovieById(id : Int ) : Result<MoviesItem>
    suspend fun getTvById(id : Int ) : Result<DiscoverItem>
}