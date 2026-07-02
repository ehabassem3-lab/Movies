package com.example.movies.routes

import com.example.movies.network.response.discover.DiscoverItem
import kotlinx.serialization.Serializable

sealed class AppRoutes {
    @Serializable
    data class TvDetailsRoute(
       val  id : Int ,
        val type : String
    ) : AppRoutes()
    @Serializable
    data class  TvFullRoute (
        val header : String ,
        val genre : Int? ,

    )
    @Serializable
    data object SplashRoute : AppRoutes()
    @Serializable
    object SearchRoute : AppRoutes()
    @Serializable
    object MainRoute : AppRoutes()
}