package com.example.movies.ui.main.tabs.home

import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.network.response.search.SearchResponse
import com.example.movies.ui.main.Resources

data class HomeStates(
    val sections: List<TvSectionUiState> = emptyList() ,
    val sectionsMovies: List<MovieSectionUiState> = emptyList()

)
data class TvSectionUiState(
    val title: String,
    val genreId: Int?,
    val state: Resources<DiscoverResponse> = Resources.idle
)
data class MovieSectionUiState(
    val title: String,
    val genreId: Int?,
    val state: Resources<MoviesResponse> = Resources.idle
)
sealed class HomeEvents{
    data class  getDiscoverTv (val page : Int? = 1 , val genre : Int?): HomeEvents()
    data object LoadHomeSections : HomeEvents()
    data object  LoadMovies : HomeEvents()

}