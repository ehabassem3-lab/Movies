package com.example.movies.ui.main.movies


import androidx.annotation.StringRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.grid.GridCells.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.routes.AppRoutes
import com.example.movies.routes.AppRoutes.*
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.home.HomeEvents.*
import com.example.movies.ui.main.tabs.home.HomeViewModel
import com.example.movies.ui.main.tabs.home.sharedHOmeViewModel
import com.example.movies.ui.theme.AppTypography
import com.example.utilities.ErrorView
import com.example.utilities.LoadingScreen
import com.example.utilities.LoadingView
import kotlin.collections.orEmpty

@Composable
fun MoviesFullView(
    genre : Int? ,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()

    ){
    @StringRes
    fun movieGenreTitleRes(genre: Int?): Int = when (genre) {
        null -> R.string.recommendations
        28 -> R.string.action
        12 -> R.string.adventure
        16 -> R.string.animation
        35 -> R.string.comedy
        80 -> R.string.crime
        99 -> R.string.documentary
        18 -> R.string.drama
        10751 -> R.string.family
        14 -> R.string.fantasy
        36 -> R.string.history
        27 -> R.string.horror
        10402 -> R.string.music
        9648 -> R.string.mystery
        10749 -> R.string.romance
        878 -> R.string.science_fiction
        53 -> R.string.thriller
        10752 -> R.string.war
        37 -> R.string.western
        else -> R.string.recommendations
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val colorScheme = MaterialTheme.colorScheme
    val currentSection = state.sectionsMovies.firstOrNull { it.genreId == genre }


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
                Text( text = stringResource(movieGenreTitleRes(genre))  , style = AppTypography.titleLarge.copy(color = colorScheme.onBackground),)
                Text("")

            }
            when(
              currentSection?.state
            ){

                is Resources.Error -> {
                    ErrorView {
                        viewModel.doAction(getDiscoverMovies(genre =genre))
                    }
                }
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
                is Resources.Success<MoviesResponse> -> {
                    val moviesList = (currentSection.state)
                        .data
                        ?.results
                        .orEmpty()
                    LazyVerticalGrid(
                        columns = Fixed(2),
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceAround,

                        ) {
                        items(moviesList, key = { it?.id!! }) {
                            MovieItem(movieItem = it) {
                                navController.navigate(
                                    TvDetailsRoute(it?.id!!, type = "Movie")
                                )
                            }
                        }
                        item {
                            when(currentSection.isLoadingMore){
                                true -> {
                                    LoadingScreen(isGrid = true)
                                }
                                false -> {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .offset(x = 100.dp)
                                            .width(170.dp)
                                            .height(70.dp)
                                            .background(colorScheme.onBackground, RoundedCornerShape(10.dp))
                                            .clickable { viewModel.doAction(OnMoreMovieClick(genre))}
                                    ) {
                                        Text("Load More", style = AppTypography.bodyMedium.copy(color = colorScheme.background, fontWeight = FontWeight.Normal))
                                    }
                                }

                            }

                        }
                    }
                }
                Resources.idle -> {}
                else -> {}
            }


        }

    }
}
