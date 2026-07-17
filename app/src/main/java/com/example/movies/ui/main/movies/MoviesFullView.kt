package com.example.movies.ui.main.movies


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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.search.MovieItem
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.home.HomeViewModel
import com.example.movies.ui.theme.AppTypography
import kotlin.collections.orEmpty

@Composable
fun MoviesFullView(
    genre : Int? ,
    navController: NavController,

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
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.state.collectAsState().value
    val colorScheme = MaterialTheme.colorScheme

    val tvList = state.sectionsMovies
        .firstOrNull { it.genreId == genre }
        ?.state
        ?.let { it as? Resources.Success }
        ?.data
        ?.results
        ?: emptyList()
    LaunchedEffect(state.page) {
        viewModel.doAction(HomeEvents.getDiscoverMovies(state.page , genre))
    }

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

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier,
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,

                ) {
                items(tvList, key = { it?.id!! }) {
                    MovieItem(movieItem = it) {}
                }
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp)
                            .height(50.dp)
                            .background(colorScheme.onBackground, RoundedCornerShape(10.dp))
                            .clickable {viewModel.doAction(HomeEvents.onMoreClick)}
                    ) {
                        Text("Load More", style = AppTypography.bodyMedium.copy(color = colorScheme.onBackground, fontWeight = FontWeight.Normal))
                    }
                }
            }
        }




    }

}
