package com.example.movies.ui.main.search

import android.telephony.ServiceState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeStates
import com.example.movies.ui.main.tabs.home.TvSection
import com.example.movies.ui.main.tv.TvFullView
import com.example.utilities.LoadingView

@Composable
fun SearchIdleView( state : SearchStates, navController: NavController){
    LazyColumn ( modifier = Modifier.fillMaxSize()) {
        item{
            val tv = (state.tvRecommendation as? Resources.Success)?.data?.results
            val movies = (state.movieRecommendation as? Resources.Success)?.data?.results
            when (state.tvRecommendation) {
                Resources.Loading -> {
                    LazyColumn {
                        items(10) {
                            LazyRow() {
                                items(10){
                                    LoadingView()
                                }
                            }
                        }
                    }
                }

                is Resources.Success -> {
                    TvSection(
                        title = stringResource(R.string.best_of_our_2026_tv_show),
                        genre = null,
                        tvList =  tv,
                        navController = navController
                    ) {
                        navController.navigate(AppRoutes.TvFullRoute(
                            genre = null ,
                        ))

                    }
                    TvSection(
                        title = stringResource(R.string.search_idle),
                        genre = null,
                        movies = movies,
                        navController = navController
                    ) {
                        navController.navigate(AppRoutes.MovieFullRoute(
                            genre = null ,
                        ))

                    }
                }

                is Resources.Error -> {

                }

                Resources.idle, null -> {

                }
            }

        }



    }

}