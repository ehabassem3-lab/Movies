package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.utilities.ErrorView

@Composable
fun MoviesFav(
    state : Resources<MoviesResponse>,
    onRetry : () -> Unit
){
    when(state){
        is Resources.Error -> { ErrorView{onRetry()} }
        Resources.Loading -> {
            LazyVerticalGrid (
                columns = GridCells.Fixed(2)
            ){
                item{


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

                    }

                }
            }
        }
        Resources.idle -> {}
    }

}