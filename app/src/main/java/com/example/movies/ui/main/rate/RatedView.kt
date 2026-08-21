package com.example.movies.ui.main.rate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.ui.main.tabs.saved.FavEvents
import com.example.movies.ui.main.tabs.saved.sharedSavedViewModel
import com.example.movies.ui.theme.AppTypography

@Composable
fun RatedView(
    navController: NavController
){

    var index by rememberSaveable{ mutableIntStateOf(0) }
    val colorScheme = MaterialTheme.colorScheme
    val viewModel = sharedSavedViewModel()
    val state = sharedSavedViewModel().state.collectAsState().value.allRatedState
    val list = listOf("All" , "Movie" , "Tv")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                contentDescription = "" ,
                tint = colorScheme.onBackground ,
                modifier = Modifier.clickable{
                    navController.popBackStack()
                }

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) ,
                contentAlignment = Alignment.Center


            ) {
                Text(
                              "Rated",
                    textAlign = TextAlign.Center,
                    style = AppTypography.titleLarge.copy(
                        color = colorScheme.onBackground ,
                        fontSize = 26.sp
                    ) ,

                    )

            }
        }

        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = index ,
                containerColor = Color.Transparent ,
                contentColor = Color.Transparent ,
                indicator = {} ,
                divider = {}

            ) {
                for (i in 0 until list.size){
                    Tab(
                        selected = i==index ,
                        onClick = {index = i} ,
                        unselectedContentColor = Color.Transparent ,
                        selectedContentColor = Color.Transparent ,

                        ) {
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                                .padding(horizontal = 5.dp)
                                .background(color = Color.Transparent, RoundedCornerShape(12.dp))
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    color = colorScheme.onBackground
                                ),
                            contentAlignment = Alignment.Center

                        ){
                            Text(
                                list[i],
                                style =  if (index == i) AppTypography.titleLarge.copy(
                                    color = colorScheme.onBackground ,
                                    fontSize = 26.sp
                                ) else
                                    AppTypography.titleMedium.copy(
                                        color =  colorScheme.onBackground ,
                                        fontWeight = FontWeight.Light ,
                                        fontSize = 18.sp
                                    )
                            )

                        }


                    }

                }

            }
            when(index){
                0 -> AllRated(state,navController){
                    viewModel.doAction(FavEvents.onGetAllRated)
                }
                1->RatedMovies(state,navController){
                    viewModel.doAction(FavEvents.onGetAllRated)
                }
                2-> RatedTvShows(state,navController){
                    viewModel.doAction(FavEvents.onGetAllRated)
                }

            }

        }
    }

}