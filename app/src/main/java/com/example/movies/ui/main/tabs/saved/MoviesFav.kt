package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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

        }
        is Resources.Success  ->{
            val movies = state.data?.results?:emptyList()
            LazyColumn() {
                items(movies){
                    MovieItem(movieItem =  it){

                    }

                }
            }
        }
        Resources.idle -> {}
    }

}