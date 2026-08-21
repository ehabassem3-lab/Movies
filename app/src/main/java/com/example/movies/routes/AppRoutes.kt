package com.example.movies.routes

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

sealed class AppRoutes {
    @Serializable
    data object  RatedRoute : AppRoutes()
    @Serializable
    data object  WatchListRoute : AppRoutes()
    @Serializable
    data class  ActorRoute(
        val id : Int
    ) : AppRoutes()

    @Serializable
    data class AuthCallbackRoute(
        val requestToken: String,
        val approved: Boolean
    )
    @Serializable
    object CreateSessionRoute : AppRoutes()
    @Serializable
    data class TvDetailsRoute(
       val  id : Int ,
        val type : String
    ) : AppRoutes()
    @Serializable
    data class  TvFullRoute (
        val genre : Int?,

        )
    @Serializable
    data class  MovieFullRoute (
        val genre : Int?,

        )
    @Serializable
    data object SplashRoute : AppRoutes()
    @Serializable
    object SearchRoute : AppRoutes()
    @Serializable
    data class MainRoute(    val index: Int = 0
    ) : AppRoutes()
}