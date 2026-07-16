package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.utilities.ErrorView
import com.example.utilities.LoadingView

@Composable
fun TvFav(
    state: Resources<DiscoverResponse>,
    onRetry: () -> Unit
) {
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
            val tv = state.data?.results?:emptyList()
            LazyVerticalGrid (
                columns = GridCells.Fixed(2)
            ) {
                items(tv){
                    MovieItem(tvItem =  it){

                    }

                }
            }
        }
        Resources.idle -> {}
    }
}