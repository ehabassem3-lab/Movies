package com.example.movies.ui.main.tabs.trending

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.TvSection
import com.example.utilities.LoadingScreen

@Composable
fun TvTrending(
    state: Resources<DiscoverResponse> ,
     navController: NavController ,
    onError : () -> Unit
){
         when(state){
             is Resources.Error -> {onError()}
             Resources.Loading -> {
                 Column(
                 modifier = Modifier.height(300.dp).fillMaxWidth()
             ) {
                 LoadingScreen(false)
             } }
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