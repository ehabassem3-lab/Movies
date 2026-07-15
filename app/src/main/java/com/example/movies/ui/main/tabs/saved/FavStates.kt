package com.example.movies.ui.main.tabs.saved

import com.example.movies.network.response.FavItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.profile.FavouriteItem
import com.example.movies.ui.main.tabs.profile.ProfileEvents

data class FavStates (
    val FavTvState : Resources<DiscoverResponse> = Resources.idle,
    val FavMovieState : Resources<MoviesResponse> = Resources.idle ,
    val allFavState: Resources<List<FavItem>> = Resources.Loading
)



sealed class FavEvents{
    object OnGetFavouriteMovie  : FavEvents()
    object OnGetFavouriteTv : FavEvents()
    object onGetAllFav : FavEvents()
}