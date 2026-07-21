package com.example.movies.ui.main.tv

import com.example.movies.network.response.FavItem
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.details.DetailsItem
import com.example.movies.network.response.details.DetailsItemResponse
import com.example.movies.network.response.details.TvDetails
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.saved.FavEvents

data class TvStates (
    val moviesApi : Resources<MoviesItem> = Resources.idle,
    val tvApi : Resources<DiscoverItem> = Resources.idle ,
    val castStateMovies : Resources<Cast> = Resources.idle ,
    val castStateTv : Resources<Cast> = Resources.idle ,
    val favState : Resources<DetailsItem> = Resources.idle ,
    val allFavState: Resources<List<FavItem>> = Resources.Loading ,
    val favApiState : Resources<Unit> = Resources.idle ,



    )

sealed class TvEvents{
    data class GetTv(val id : Int) : TvEvents()
    data class GetMovie(val id : Int) : TvEvents()
    data class getMoviesCast (val id : Int): TvEvents ()
    data class getTvCast (val id : Int): TvEvents ()
    data class getDetails (val  id : Int , val mediaType: String ) : TvEvents()
    data class  addToFavoutire(val item: DiscoverItem? = null, val mediaId: Int, val  mediaType: String, val favorite: Boolean ) : TvEvents()

}




