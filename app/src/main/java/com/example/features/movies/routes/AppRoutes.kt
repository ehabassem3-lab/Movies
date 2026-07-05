package com.example.features.movies.routes

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

sealed class AppRoutes {

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
    data object SplashRoute : AppRoutes()
    @Serializable
    object SearchRoute : AppRoutes()
    @Serializable
    object MainRoute : AppRoutes()
}