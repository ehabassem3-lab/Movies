package com.example.movies.ui.main.tv

import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.ui.main.Resources

data class TvStates (
    val moviesApi : Resources<MoviesItem> = Resources.idle ,
    val tvApi : Resources<DiscoverItem> = Resources.idle

)

sealed class TvEvents{
    data class GetTv(val id : Int) : TvEvents()
    data class GetMovie(val id : Int) : TvEvents()

}




