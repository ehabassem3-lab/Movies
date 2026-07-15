package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movies.network.response.FavItem
import com.example.movies.ui.main.Resources
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import com.example.movies.mapper.toDiscoverItem
import com.example.movies.mapper.toMovieItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.ui.main.search.MovieItem
import com.example.movies.ui.main.tabs.profile.FavouriteItem
import com.example.utilities.LoadingView

@Composable
fun Allfav(
    state: Resources<List<FavItem>>
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when(state){
            is Resources.Error -> {}
            Resources.Loading -> {

            }
            is Resources.Success<List<FavItem>> -> {
                val all = state.data

                LazyVerticalGrid (
                    columns = GridCells.Fixed(2)
                ){
                    items(all!!) {
                        if (it.mediaType == "movie"){
                            MovieItem(movieItem = it.toMovieItem()){}
                        }else{
                            MovieItem(tvItem = it.toDiscoverItem()){}

                        }

                    }
                }
            }
            Resources.idle -> {}
        }
    }


}
@Preview
@Composable
fun Preview(){
    LoadingView()

}
