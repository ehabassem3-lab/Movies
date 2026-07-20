package com.example.movies.ui.main.actor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movies.App
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.network.response.cast.ActorWorkItem
import com.example.movies.network.response.cast.CastItem
import com.example.movies.ui.theme.AppTypography
import kotlinx.coroutines.async
import kotlinx.io.files.Path

@Composable
fun ActorsWork(
    actorWork: List<ActorWorkItem?>? ,
    actorName: String
){
    Column(
        modifier = Modifier.height(700.dp).fillMaxWidth()
    ) {
        LazyRow() {
            items(actorWork!!){
                if (it?.mediaType == "movie") {
                    Column(
                        modifier = Modifier.height(350.dp).fillMaxWidth()
                    ) {
                        Text("Movies Stared ${actorName}" , style = AppTypography.titleLarge)

                            Box(modifier = Modifier.size(200.dp).clip(CircleShape)){
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500${it?.posterPath}" ,
                                    contentDescription = "" ,
                                    contentScale = ContentScale.FillBounds ,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Text(
                                it?.name?:""  ,
                                style = AppTypography.titleLarge
                            )
                        }


                }
                else{

                }


                }






        }


    }



}