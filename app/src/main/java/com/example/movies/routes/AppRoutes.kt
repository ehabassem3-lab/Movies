package com.example.movies.routes

import kotlinx.serialization.Serializable

sealed class AppRoutes {
    @Serializable
    object Home : AppRoutes()
}