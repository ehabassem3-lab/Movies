package com.example.movies.ui.main.tabs.trending

import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.Resources

data class TrendingState (
    val MoviesapiState : Resources<MoviesResponse> = Resources.Loading ,
    val TvapiState : Resources<DiscoverResponse> = Resources.Loading

)


sealed class TrendingEvents{
    data object getMovies : TrendingEvents()
    data object getTv: TrendingEvents()
}
