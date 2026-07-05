package com.example.features.movies.ui.main.tabs.home

import androidx.compose.foundation.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography

@Composable
fun HomeView(
    navController: NavController
){

    val colorScheme = MaterialTheme.colorScheme
   val viewModel = hiltViewModel<HomeViewModel>()
   val state = viewModel.state.collectAsState().value
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val genres = listOf<String>(
        stringResource(com.example.movies.R.string.movies),
        "",
        stringResource(com.example.movies.R.string.tv_shows),


    )

    LaunchedEffect(Unit){
        viewModel.doAction(HomeEvents.LoadHomeSections)
        viewModel.doAction(HomeEvents.LoadMovies)

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
                val isSelected = selectedTabIndex == i
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
            println("Movies first item = ${(state.sectionsMovies.firstOrNull()?.state as? Resources.Success)?.data?.results?.firstOrNull()}")
            println("TV first item = ${(state.sections.firstOrNull()?.state as? Resources.Success)?.data?.results?.firstOrNull()}")
            when(selectedTabIndex){
                0-> {
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
                2->{
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






            }

        }

    }
}