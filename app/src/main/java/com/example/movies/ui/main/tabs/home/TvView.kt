package com.example.movies.ui.main.tabs.home

import android.media.tv.TvView
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.utilities.ErrorView
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer
import kotlin.collections.orEmpty

@Composable
fun TvView(state : HomeStates , navController: NavController , onRetry : () -> Unit  ){
    Column(
        modifier =  Modifier.fillMaxSize()
    ) {

        when {
            state.sections.all { it.state is Resources.Success } -> {
                LazyColumn {
                    items(state.sections) { section ->

                        val tvList = (section.state as? Resources.Success)
                            ?.data
                            ?.results
                            .orEmpty()

                        TvSection(
                            genre = section.genreId ,
                            title = stringResource(section.title),
                            tvList = tvList,
                            navController = navController,
                            onViewAll = {
                                navController.navigate(
                                    AppRoutes.TvFullRoute( section.genreId)
                                )
                            }
                        )
                    }
                }
            }

            state.sections.any { it.state is Resources.Loading } -> {
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

            state.sections.any { it.state is Resources.Error } -> {
                Column(
                    modifier = Modifier.fillMaxSize() ,
                    verticalArrangement = Arrangement.Center ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { ErrorView { onRetry() } }
            }
        }
    }

}