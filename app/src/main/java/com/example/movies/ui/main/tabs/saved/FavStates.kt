package com.example.movies.ui.main.tabs.saved

import com.example.movies.network.response.FavItem
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.profile.FavouriteItem
import com.example.movies.ui.main.tabs.profile.ProfileEvents

data class FavStates (
    val FavTvState : Resources<DiscoverResponse> = Resources.idle,
    val FavMovieState : Resources<MoviesResponse> = Resources.idle,
    val allFavState: Resources<List<FavItem>> = Resources.Loading,
    val favApiState : Resources<Unit> = Resources.idle,
    val watchListState: Resources<Unit> = Resources.idle,
    val WatchListTvState : Resources<DiscoverResponse> = Resources.idle,
    val WatchMovieState : Resources<MoviesResponse> = Resources.idle,
    val allWatchListState: Resources<List<FavItem>> = Resources.idle,
    val TvRatingApiState : Resources<Unit> = Resources.idle,
    val MovieRatingApiState : Resources<Unit> = Resources.idle,
    val RatedMoviesState : Resources<MoviesResponse> = Resources.idle,
    val RatedTvState : Resources<DiscoverResponse> = Resources.idle,
    val allRatedState : Resources<List<FavItem>> = Resources.idle ,
    val rates: Map<Int, Double> = emptyMap()




)



sealed class FavEvents{
    data class onIncreaseRate(val id : Int): FavEvents()
    data class onDecreaseRate(val id : Int): FavEvents()

    data  class onRateMovie (val id : Int , val rate : Double , val item : DiscoverItem? = null , val rated : Boolean = false , val mediaType: String): FavEvents()
    data  class onRateTv(val id : Int , val rate : Double , val item : DiscoverItem? = null , val rated : Boolean = false , val mediaType: String): FavEvents()

    object onGetRatedMovies : FavEvents()
    object onGetRatedTv: FavEvents()
    object onGetAllRated : FavEvents()




    object OnGetFavouriteMovie  : FavEvents()
    object OnGetFavouriteTv : FavEvents()
    object onGetAllFav : FavEvents()
    object OnGetWatchListMovie  : FavEvents()
    object OnGetWatchListTv : FavEvents()
    object onGetAllWatchList : FavEvents()
    data class   onAddToWatchList (val mediaId: Int, val  mediaType: String, val watchList: Boolean , val item : DiscoverItem? = null )  : FavEvents()
    data class  addToFavoutire(val item: DiscoverItem? = null, val mediaId: Int, val  mediaType: String, val favorite: Boolean ) : FavEvents()

}