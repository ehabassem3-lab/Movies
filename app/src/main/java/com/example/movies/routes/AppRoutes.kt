package com.example.movies.routes

import kotlinx.serialization.Serializable

sealed class AppRoutes {
    @Serializable
    data object SplashRoute : AppRoutes()
    @Serializable
    object SearchRoute : AppRoutes()
    @Serializable
    object MainRoute : AppRoutes()
}