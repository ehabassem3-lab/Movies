package com.example.movies.ui.main.tabs.home

import androidx.compose.foundation.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography

@Composable
fun HomeView(
    navController: NavController ,
    viewModel: HomeViewModel = hiltViewModel()
){

    val colorScheme = MaterialTheme.colorScheme
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val genres = listOf(
        stringResource(com.example.movies.R.string.movies),

        stringResource(com.example.movies.R.string.tv_shows),
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositons ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .tabIndicatorOffset(tabPositons[selectedTabIndex])
                        .width(10.dp)
                        .height(1.dp)
                        .background(colorScheme.onBackground)
                )


            },
            divider = {

            }


        ) {
            for (i in 0 until (genres.size)) {
                val isSelected = selectedTabIndex == i
                Tab(
                    modifier = Modifier.padding(horizontal = 30.dp , vertical = 10.dp),
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
                            if (isSelected) AppTypography.titleLarge.copy(color = colorScheme.onBackground) else AppTypography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp ,
                                color =  colorScheme.onBackground
                            )
                    )
                }

        }

        }
        Spacer(modifier = Modifier.size(10.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            when(selectedTabIndex) {
                0->  MoviesView(state,navController){viewModel.doAction(HomeEvents.LoadMovies)}
                1->TvView(state ,navController){viewModel.doAction(HomeEvents.LoadHomeSections)}



            }

        }

    }
}