package com.example.movies.ui.main.actor

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.shadow
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
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography
import com.example.utilities.ErrorView
import com.example.utilities.ExpandableText
import com.example.utilities.LoadingScreen
import com.example.utilities.SeparationLine
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
        modifier = Modifier.fillMaxSize().background(colorScheme.background)
    ) {
        LazyColumn() {

            item {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(40.dp)

                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = colorScheme.onBackground,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            }

            when (val actorState = state.actorState) {
                is Resources.Success -> {
                    val actor = actorState.data

                    item {
                        Column(
                            modifier = Modifier

                                .padding(top = 10.dp)
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(350.dp)
                                    .clip(CircleShape)
                            ) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500${actor?.profilePath}",
                                    contentDescription = null,
                                    error = painterResource(R.drawable.ic_video),
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    modifier =  Modifier.padding(20.dp),
                                    text = actor?.name.orEmpty(),
                                    style = AppTypography.titleLarge.copy(
                                        color = colorScheme.onBackground,
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                )

                                Text(
                                    text = when (actor?.gender) {
                                        1 -> "Female"
                                        2 -> "Male"
                                        3 -> "Non-binary"
                                        else -> "Unknown"
                                    },
                                    modifier = Modifier.padding(start = 10.dp),
                                    style = AppTypography.titleLarge.copy(
                                        color = colorScheme.onBackground,
                                        fontSize = 16.sp
                                    )
                                )

                                Icon(
                                    painter = if (actor?.gender == 2)
                                        painterResource(R.drawable.ic_male)
                                    else
                                        painterResource(R.drawable.ic_female),
                                    contentDescription = null,
                                    tint = colorScheme.onBackground,
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                        .size(20.dp)
                                )
                            }

                            ExpandableText(

                                text = actor?.biography.orEmpty(),
                                collapsedLines = 10
                            )

                                SeparationLine()

                        }
                    }
                }
                else -> {}
            }

            when (val workState = state.workState) {
                is Resources.Success -> {
                    val actor = (state.actorState as? Resources.Success)?.data
                    val cast = workState.data?.cast
                    val crew = workState.data?.crew

                    item {
                        ActorMovies(
                            actorWork = cast,
                            actorName = actor?.name.orEmpty() ,
                            navController
                        )
                    }
                    item {
                        SeparationLine()
                    }

                    item {
                        ActorTvShows(
                            actorWork = cast,
                            actorName = actor?.name.orEmpty(),
                            navController
                        )
                    }
                    item {
                        SeparationLine()
                    }


                    item {
                        ActorOtherMovies(
                            actorWork = crew,
                            actorName = actor?.name.orEmpty(),
                            navController
                        )
                    }
                }

                else -> {}
            }
        }



        }


    }



