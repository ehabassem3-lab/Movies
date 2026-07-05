package com.example.features.movies.ui.main.tv

import com.example.movies.network.response.details.DetailsItemResponse
import com.example.movies.network.response.details.TvDetails
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.ui.main.Resources

data class TvStates (
    val moviesApi : Resources<DetailsItemResponse> = Resources.idle ,
    val tvApi : Resources<TvDetails> = Resources.idle

)

sealed class TvEvents{
    data class GetTv(val id : Int) : TvEvents()
    data class GetMovie(val id : Int) : TvEvents()

}




