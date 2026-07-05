package com.example.features.movies.ui.main.tv

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
fun TvFullView(
    genre : Int? ,
    navController: NavController,

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
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.state.collectAsState().value
    val colorScheme = MaterialTheme.colorScheme

    var page by rememberSaveable() {  mutableIntStateOf(1)}
    val tvList = state.sections
        .firstOrNull { it.genreId == genre }
        ?.state
        ?.let { it as? Resources.Success }
        ?.data
        ?.results
        ?: emptyList()
    LaunchedEffect(page) {
        viewModel.doAction(HomeEvents.getDiscoverTv(page , genre))
    }

    println(tvList )
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
                    modifier = Modifier.size(24.dp).clickable{ navController.navigate(AppRoutes.MainRoute) })
                Text( text = stringResource(genreTitleRes(genre))  , style = AppTypography.titleLarge.copy(color = colorScheme.onBackground),)
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
                    MovieItem(tvItem = it) {}
                }
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp)
                            .height(50.dp)
                            .background(colorScheme.onBackground, RoundedCornerShape(10.dp))
                            .clickable {
                                page += 1
                            }
                    ) {
                        Text("Load More", style = AppTypography.bodyMedium.copy(color = colorScheme.onBackground, fontWeight = FontWeight.Normal))
                    }
                }
            }
        }




    }

}
