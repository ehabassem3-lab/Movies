package com.example.movies.ui.main.tv

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.home.HomeViewModel
import com.example.movies.ui.main.tabs.home.sharedHOmeViewModel
import com.example.movies.ui.theme.AppTypography
import com.example.utilities.LoadingView
import kotlin.collections.orEmpty

@Composable
fun TvFullView(
    genre : Int? ,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()


){
    @StringRes
    fun genreTitleRes(genre: Int?): Int = when (genre) {
        null -> R.string.recommendations
        35 -> R.string.comedy
        18 -> R.string.drama
        16 -> R.string.animation
        80 -> R.string.crime
        10759 -> R.string.action_adventure
        10751 -> R.string.family
        9648 -> R.string.mystery
        10762 -> R.string.kids
        10768 -> R.string.war_politics
        else -> R.string.recommendations
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val colorScheme = MaterialTheme.colorScheme
    val currentSection = state.sections.firstOrNull { it.genreId == genre }
    val tvList = (currentSection?.state as? Resources.Success)?.data?.results.orEmpty()


    Scaffold (
        modifier = Modifier.fillMaxSize().background(colorScheme.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorScheme.background)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp) ,
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                    contentDescription = "" ,
                    tint = colorScheme.onBackground ,
                    modifier = Modifier.size(24.dp).clickable{ navController.popBackStack()})
                Text( text = stringResource(genreTitleRes(genre))  , style = AppTypography.titleLarge.copy(color = colorScheme.onBackground),)
                Text("")

            }

            when(
                currentSection?.state
            ){
                is Resources.Error -> {}
                Resources.Loading -> {

                    LazyColumn {
                        items(10) {
                            LazyRow() {
                                items(10){
                                    LoadingView()
                                }
                            }
                        }
                    }
                }
                is Resources.Success<DiscoverResponse> -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceAround,

                        ) {
                        items(tvList, key = { it?.id!! }) {
                            MovieItem(tvItem = it) {
                                navController.navigate(AppRoutes.TvDetailsRoute(it?.id!! , type = "TV" ))
                            }
                        }
                        item {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .offset(x = 100.dp)
                                    .width(170.dp)
                                    .height(70.dp)
                                    .background(colorScheme.onBackground, RoundedCornerShape(10.dp))
                                    .clickable {  viewModel.doAction(
                                        HomeEvents.OnMoreClick(genre)
                                    )}
                            ) {
                                Text("Load More", style = AppTypography.bodyMedium.copy(color = colorScheme.background, fontWeight = FontWeight.Normal))
                            }
                        }
                    }
                }
                Resources.idle -> {}
                null -> {}
            }


        }




    }

}
