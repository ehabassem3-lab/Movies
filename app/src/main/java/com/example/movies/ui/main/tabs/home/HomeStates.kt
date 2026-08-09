package com.example.movies.ui.main.tabs.home

import androidx.annotation.StringRes
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.network.response.search.SearchResponse
import com.example.movies.ui.main.Resources

data class HomeStates(
    val sections: List<TvSectionUiState> = emptyList() ,
    val sectionsMovies: List<MovieSectionUiState> = emptyList() ,
)
data class TvSectionUiState(
    @param:StringRes val title: Int,
    val genreId: Int?,
    val state: Resources<DiscoverResponse> = Resources.idle,
    val page: Int = 1,
    val isLoadingMore: Boolean = false
)
data class MovieSectionUiState(
    @param:StringRes val title: Int,
    val genreId: Int?,
    val state: Resources<MoviesResponse> = Resources.idle,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
)
sealed class HomeEvents{
    data class  getDiscoverTv (val page : Int? = 1 , val genre : Int?): HomeEvents()
    data class  getDiscoverMovies (val page : Int? = 1 , val genre : Int?): HomeEvents()

    data object LoadHomeSections : HomeEvents()
    data object  LoadMovies : HomeEvents()
    data class OnMoreTvClick(val genre: Int?) : HomeEvents()

    data class OnMoreMovieClick(val genre: Int?) : HomeEvents()


}