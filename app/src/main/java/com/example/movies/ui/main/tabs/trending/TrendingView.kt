package com.example.movies.ui.main.tabs.trending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.utilities.SeparationLine
import javax.annotation.meta.When

@Composable
fun TrendingView(
    navController: NavController
){
    val viewModel = hiltViewModel<TrendingViewModel>()
    val state = viewModel.state.collectAsState().value


    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            MoviesTrending(state.MoviesapiState, navController ){
                viewModel.doAction(TrendingEvents.getMovies)
            }


        }
        item{
            SeparationLine()
        }
         item {
             TvTrending(state.TvapiState , navController){
                 viewModel.doAction(TrendingEvents.getTv)
             }


         }
    }
}