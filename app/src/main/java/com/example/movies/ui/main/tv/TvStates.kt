package com.example.movies.ui.main.tv

import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.details.DetailsItemResponse
import com.example.movies.network.response.details.TvDetails
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeEvents

data class TvStates (
    val moviesApi : Resources<MoviesItem> = Resources.idle,
    val tvApi : Resources<DiscoverItem> = Resources.idle ,
    val castStateMovies : Resources<Cast> = Resources.idle ,
    val castStateTv : Resources<Cast> = Resources.idle ,


    )

sealed class TvEvents{
    data class GetTv(val id : Int) : TvEvents()
    data class GetMovie(val id : Int) : TvEvents()
    data class getMoviesCast (val id : Int): TvEvents ()
    data class getTvCast (val id : Int): TvEvents ()
}




