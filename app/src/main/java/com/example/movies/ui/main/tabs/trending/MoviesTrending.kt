package com.example.movies.ui.main.tabs.trending

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.MoviesView
import com.example.movies.ui.main.tabs.home.TvSection

@Composable
fun MoviesTrending(
    state: Resources<MoviesResponse> ,
    navController : NavController
){
         when(state){
             is Resources.Error ->{}
             Resources.Loading -> {}
             is Resources.Success<MoviesResponse> -> {
                 val movies = state.data?.results ?: emptyList()
                 TvSection(
                     title = "Todays Trending Movies",
                     genre = null,
                     movies = movies,
                     navController = navController
                 ) { }
             }
             Resources.idle -> {}
         }

}