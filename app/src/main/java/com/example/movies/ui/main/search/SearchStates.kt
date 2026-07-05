package com.example.movies.ui.main.search

import com.example.movies.network.response.search.SearchResponse
import com.example.movies.ui.main.Resources

data class SearchStates (
    val apiState : Resources<SearchResponse> = Resources.idle ,
    val search : String? = null
)
sealed class SearchEvent{
    data class onSearchClick(val search : String)  : SearchEvent()
    data class onSearchChangeing(val search : String) : SearchEvent()
}