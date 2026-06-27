package com.example.movies.ui.main.tabs.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.ui.theme.AppTypography

@Composable
fun HomeView(){
    val colorScheme = MaterialTheme.colorScheme
   val viewModel = hiltViewModel<HomeViewModel>()
   val state = viewModel.state.collectAsState().value
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val genres = listOf<String>(
        "Movies" ,
        "Tv Shows " ,
        "Anime " ,


    )

    LaunchedEffect(Unit){

    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositons ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositons[selectedTabIndex])
                        .height(2.dp)
                        .background(colorScheme.onBackground)
                )


            },
            divider = {

            }


        ) {
            for (i in 0 until (genres.size)) {
                val isSlected = selectedTabIndex == i
                Tab(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    selected = selectedTabIndex == i,
                    onClick = {
                        selectedTabIndex = i
                    }
                ) {
                    Text(
                        text = genres[i],
                        color = colorScheme.onBackground,
                        modifier = Modifier.padding(2.dp),
                        style =
                            if (isSlected) AppTypography.titleLarge.copy(color = colorScheme.onBackground) else AppTypography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp ,
                                color =  colorScheme.onBackground
                            )
                    )
                }
            }
        }


    }
}