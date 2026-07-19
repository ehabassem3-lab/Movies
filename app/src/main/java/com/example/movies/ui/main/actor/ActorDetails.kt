package com.example.movies.ui.main.actor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.movies.R
import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.ui.main.Resources
import io.ktor.sse.SPACE

@Composable
fun ActorDetails(
    id: Int  ,
     navController: NavController
){
    val viewModel = hiltViewModel<ActorViewModel>()
    val state = viewModel.states.collectAsState().value
    val colorScheme = MaterialTheme.colorScheme
    LaunchedEffect(Unit) {
        viewModel.doAction(ActorEvents.onGetActor(id))
        viewModel.doAction(ActorEvents.onGetActorWork(id))
    }
    Column(
        modifier = Modifier.fillMaxSize().background(colorScheme.background).padding(20.dp)
    ) {
        LazyColumn() {
            item {
                Row(
                    modifier =  Modifier.fillMaxWidth().height(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "" ,
                        tint =  colorScheme.onBackground ,
                        modifier = Modifier.clickable{
                            navController.popBackStack()
                        }
                    )

                }
                when(state.actorState){
                    is Resources.Error -> {}
                    Resources.Loading -> {}
                    is Resources.Success<Actor> ->{
                        val actor = state.actorState.data

                        Column(
                            modifier = Modifier.padding(top = 10.dp).fillMaxSize()  ,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier.size(250.dp).clip(CircleShape)
                            ){
                                AsyncImage(
                                    model =   "https://image.tmdb.org/t/p/w500${actor?.profilePath}" ,
                                    contentDescription = "" ,
                                    error = painterResource(R.drawable.ic_video) ,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.fillMaxSize()
                                )

                            }
                            Spacer(modifier = Modifier.size(500.dp))
                        }
                    }
                    Resources.idle -> {}
                }
                when(state.workState){
                    is Resources.Error -> {}
                    Resources.Loading ->{}
                    is Resources.Success<ActorWork> -> {
                        val actor =( state.actorState as? Resources.Success)?.data
                        val work = state.workState.data?.cast
                        Log.e("Work" , "$work")
                            ActorsWork(work, actorName =actor?.name?:"" )

                    }
                    Resources.idle -> {}
                }

            }
        }





        }


    }



