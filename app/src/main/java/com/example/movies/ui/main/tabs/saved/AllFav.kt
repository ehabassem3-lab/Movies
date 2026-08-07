package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.mapper.toDiscoverItem
import com.example.movies.mapper.toMovieItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.search.MovieItem
import com.example.movies.ui.main.tabs.profile.FavouriteItem
import com.example.utilities.LoadingView
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer
import io.ktor.sse.COLON

@Composable
fun Allfav(
    state: Resources<List<FavItem>> ,
    navController: NavController ,
     onRetry : () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is Resources.Error -> { onRetry() }
            Resources.Loading -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    item(10){
                        LoadingView()
                    }
                }

            }

            is Resources.Success<List<FavItem>> -> {
                val all = state.data

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(all!!) {
                        if (it.mediaType == "movie") {
                            MovieItem(movieItem = it.toMovieItem()) {
                                navController.navigate(AppRoutes.TvDetailsRoute(
                                    it?.id!!,
                                     "Movie"
                                ))
                            }
                        } else {
                            MovieItem(tvItem = it.toDiscoverItem()) {
                                navController.navigate(AppRoutes.TvDetailsRoute(
                                    it?.id!!,
                                    "TV"
                                ))
                            }

                        }

                    }
                }
            }

            Resources.idle -> {}
        }
    }


}
