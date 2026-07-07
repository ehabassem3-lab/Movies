package com.example.movies.ui.main.tabs.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import coil3.compose.AsyncImage
import com.example.movies.R
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography
import com.example.utilities.ErrorView
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer
import kotlin.collections.orEmpty

@Composable
fun MoviesView(
    state  : HomeStates ,
    navController: NavController ,
    onRetry : () -> Unit
){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.sectionsMovies.all { it.state is Resources.Success } -> {
                LazyColumn {
                    items(state.sectionsMovies) { sectionsMovies ->
                        val tvList = (sectionsMovies.state as? Resources.Success)
                            ?.data
                            ?.results
                            .orEmpty()
                        TvSection(
                            genre = sectionsMovies.genreId ,
                            title = stringResource(sectionsMovies.title),
                            movies = tvList,
                            navController = navController,
                            onViewAll = {
                                navController.navigate(
                                    AppRoutes.TvFullRoute(sectionsMovies.genreId)
                                )
                            }
                        )
                    }
                }
            }

            state.sectionsMovies.any { it.state is Resources.Loading } -> {

                LazyColumn {
                    items(10) {
                     LazyRow() {
                         items(10){
                             ShimmerContainer(
                                 modifier = Modifier
                                     .padding(horizontal = 8.dp, vertical = 8.dp)
                                     .width(150.dp)
                                     .height(250.dp)
                                     .background(Color.White)
                                     .shadow(1.dp, shape = RoundedCornerShape(10.dp))
                                     .clip(RoundedCornerShape(10.dp))
                             ) {
                                 Column(
                                     modifier = Modifier
                                         .padding(horizontal = 8.dp, vertical = 8.dp)
                                         .width(200.dp)
                                         .height(300.dp)
                                         .background(Color.Gray)
                                     ,
                                     verticalArrangement = Arrangement.Center ,
                                     horizontalAlignment = Alignment.CenterHorizontally
                                 ) {

                                     Box(
                                         modifier = Modifier
                                             .fillMaxWidth()
                                             .height(280.dp)
                                             .background(Color.Gray)
                                             .clip(RoundedCornerShape(26.dp)),

                                     )

                                     Spacer(modifier = Modifier.size(8.dp))
                                     Box(

                                         modifier = Modifier
                                             .fillMaxWidth()
                                             .background(Color.Gray)
                                             .height(20.dp),
                                     )
                                     Spacer(modifier = Modifier.size(8.dp))

                                     Box(

                                         modifier = Modifier
                                             .fillMaxWidth()
                                             .background(Color.Gray)
                                             .height(20.dp),
                                     )




                                 }
                             }

                         }
                     }
                    }
                }
            }

            state.sectionsMovies.any { it.state is Resources.Error } -> {
                Column(
                    modifier = Modifier.fillMaxSize() ,
                     verticalArrangement = Arrangement.Center ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { ErrorView { onRetry() } }

            }
        }
    }




}