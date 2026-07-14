package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.utilities.ErrorView

@Composable
fun TvFav(
    state: Resources<DiscoverResponse>,
    onRetry: () -> Unit
) {
    when(state){
        is Resources.Error -> { ErrorView{onRetry()} }
        Resources.Loading -> {

        }
        is Resources.Success  ->{
            val tv = state.data?.results?:emptyList()
            LazyColumn() {
                items(tv){
                    MovieItem(tvItem =  it){

                    }

                }
            }
        }
        Resources.idle -> {}
    }
}