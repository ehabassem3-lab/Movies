package com.example.movies.ui.main.tabs.saved

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.ui.theme.AppTypography

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun SavedView(
    navController: NavController
){
    val viewModel = sharedSavedViewModel()

    val state = viewModel.state.collectAsState().value
    val colorScheme = MaterialTheme.colorScheme
    val fav = listOf( "All","Movies" ,  "Tv"  )
    var selecteTab  by rememberSaveable { mutableStateOf(0) }
    LaunchedEffect(state.lastFav) {
        viewModel.doAction(FavEvents.onGetAllFav)
        viewModel.doAction(FavEvents.OnGetFavouriteTv)
        viewModel.doAction(FavEvents.OnGetFavouriteMovie)

    }

    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()

    ) {

        Column(
            modifier = Modifier.fillMaxSize()  ,
            horizontalAlignment = Alignment.Start
        ) {
           TabRow (
                selectedTabIndex = selecteTab,
                indicator = {},
                divider = {},


            ) {
                for (i in 0 until (fav.size)) {
                    val isSelected = selecteTab == i
                    Tab(

                        modifier = Modifier.padding(horizontal = 8.dp),
                        selected = selecteTab == i,
                        onClick = {
                            selecteTab = i
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .width(120.dp)
                                .height(40.dp)
                                .border(
                                    width = 1.dp,
                                    color = colorScheme.onBackground,
                                    RoundedCornerShape(12.dp)
                                )
                                .background(
                                    if (isSelected) colorScheme.background.copy(alpha = .5f)
                                    else colorScheme.background.copy(alpha = .8f),
                                    RoundedCornerShape(12.dp)
                                )
                            ,
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = fav[i],
                                color = colorScheme.onBackground,
                                modifier = Modifier.padding(2.dp),
                                style =
                                    if (isSelected) AppTypography.titleLarge.copy(color = colorScheme.background) else AppTypography.bodyMedium.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp ,
                                        color =  colorScheme.background
                                    )
                            )
                        }

                    }

                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                when(selecteTab){
                    0->Allfav(state.allFavState)
                    1-> MoviesFav(state.FavMovieState){}
                    2-> TvFav(state.FavTvState){}

                }

            }
        }

    }
}