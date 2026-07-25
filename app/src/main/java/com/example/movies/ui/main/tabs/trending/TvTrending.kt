package com.example.movies.ui.main.tabs.trending

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.TvSection

@Composable
fun TvTrending(
    state: Resources<DiscoverResponse> ,
     navController: NavController
){
         when(state){
             is Resources.Error -> {}
             Resources.Loading -> {}
             is Resources.Success<DiscoverResponse> -> {
                 val tv = state.data?.results?:emptyList()
                 TvSection(
                     title = "Todays Trending Tv Shows",
                     genre = null,
                     tvList = tv,
                     navController  = navController
                 ) { }
             }
             Resources.idle -> {}
         }
}