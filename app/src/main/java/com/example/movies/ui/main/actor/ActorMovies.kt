package com.example.movies.ui.main.actor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.movies.R
import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.network.response.cast.ActorWorkItem
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography

@Composable
fun ActorMovies(
    actorWork: List<ActorWorkItem?>? ,
    actorName: String ,
    navController: NavController
){
    val colorScheme = MaterialTheme.colorScheme
    val movies = actorWork?.filter { it?.mediaType == "movie" }
    if (!movies.isNullOrEmpty()){
        Column(
            modifier = Modifier.padding(10.dp).height(340.dp).fillMaxWidth()
        ) {
            Text("Movies Stared ${actorName}" ,            modifier = Modifier.padding(bottom = 20.dp , top = 20.dp),
                style = AppTypography.titleLarge.copy(color = colorScheme.onBackground , fontWeight = FontWeight.Normal))
            LazyRow() {
                items(actorWork){
                    if (it?.mediaType == "movie") {
                        Column(
                            modifier = Modifier.padding(horizontal = 10.dp).height(290.dp).width(300.dp).clickable{
                             navController.navigate(AppRoutes.TvDetailsRoute(it.id!!, "Movie"))
                            } ,

                            verticalArrangement = Arrangement.Center ,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(modifier = Modifier.width(300.dp).height(200.dp).clip(RoundedCornerShape(15.dp))){
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500${it?.posterPath}" ,
                                    contentDescription = "" ,
                                    contentScale = ContentScale.FillBounds ,
                                    modifier = Modifier.fillMaxSize() ,
                                    error = painterResource(R.drawable.ic_splash)
                                )
                            }
                            Text(
                                it.title?:"sssssss"  ,
                                modifier = Modifier.padding(vertical = 25.dp).height(20.dp),
                                style = AppTypography.titleLarge.copy(color = colorScheme.onBackground , fontSize = 18.sp , fontWeight = FontWeight.Normal)

                            )
                        }


                    }


                }









            }


        }
    }
    else{
        Column(
            modifier = Modifier.height(270.dp).fillMaxWidth() ,
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "${actorName} has no movies yet" ,
                modifier = Modifier.padding(bottom = 5.dp , top = 5.dp),
                style = AppTypography.titleLarge.copy(color = colorScheme.onBackground , fontWeight = FontWeight.Normal)
            )
            Icon(
                painterResource(R.drawable.ic_no_vedio) ,
                contentDescription = "" ,
                tint =    colorScheme.onBackground ,
                modifier =  Modifier.size(50.dp)
            )

        }

    }


}