package com.example.movies.ui.main.watchlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movies.mapper.toDiscoverItem
import com.example.movies.mapper.toMovieItem
import com.example.movies.network.response.FavItem
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.utilities.ErrorView
import com.example.utilities.LoadingView

@Composable
fun TvWatchList(
    state :Resources<List<FavItem>?>? ,
    navController: NavController ,
    onRetry : () -> Unit
){
    when(state){
        is Resources.Error -> { ErrorView{onRetry()} }
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
            val movies = (state.data)?.filter { it.mediaType == "tv" }
            LazyVerticalGrid (
                columns = GridCells.Fixed(2)
            ){
                items(movies!!){
                    MovieItem(tvItem =  it.toDiscoverItem()){
                        navController.navigate(AppRoutes.TvDetailsRoute(
                            it?.id!!,
                            "TV"
                        ))
                    }

                }
            }
        }
        Resources.idle -> {}
        else -> {}
    }
}