package com.example.movies.ui.main.tabs.home

import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.search.SearchResponse
import com.example.movies.ui.main.Resources

data class HomeStates (
    val apiState : Resources<DiscoverResponse> = Resources.idle ,
)
sealed class HomeEvents{
    object  getDiscoverTv : HomeEvents()
}