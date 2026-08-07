package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.utilities.ErrorView
import com.example.utilities.LoadingView

@Composable
fun MoviesFav(
    state : Resources<MoviesResponse>,
    navController: NavController ,
    onRetry : () -> Unit
){

    when(state){
        is Resources.Error -> {onRetry()}
        Resources.Loading -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                item(10){
                    LoadingView()
                }
            }

        }
        is Resources.Success  ->{
            val movies = state.data?.results?:emptyList()
            LazyVerticalGrid (
                columns = GridCells.Fixed(2)
            ){
                items(movies){
                    MovieItem(movieItem =  it){
                          navController.navigate(AppRoutes.TvDetailsRoute(
                              it?.id!!,
                              "Movie"
                          ))
                    }

                }
            }
        }
        Resources.idle -> {}
    }

}