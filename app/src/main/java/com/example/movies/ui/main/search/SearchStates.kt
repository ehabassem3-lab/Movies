package com.example.movies.ui.main.search

import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.network.response.search.SearchResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.MovieSectionUiState
import com.example.movies.ui.main.tabs.home.TvSectionUiState

data class SearchStates (
    val apiState : Resources<SearchResponse> = Resources.idle,
    val tvRecommendation: Resources<DiscoverResponse> = Resources.idle,
    val movieRecommendation: Resources<MoviesResponse> = Resources.idle,
    val search : String? = null

)
sealed class SearchEvent{
    data class onSearchClick(val search : String , val page : Int)  : SearchEvent()
    data class onSearchChangeing(val search : String) : SearchEvent()
    object  loadData : SearchEvent()
}