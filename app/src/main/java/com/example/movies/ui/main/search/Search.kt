package com.example.movies.ui.main.search

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.network.response.search.ResultsItem
import com.example.movies.network.response.search.SearchResponse
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.CustomTextField
import com.example.movies.ui.main.EmptyView
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.MovieItem
import com.example.movies.ui.main.tabs.home.TvSection
import com.example.movies.ui.theme.AppTypography
import com.example.utilities.ErrorView
import com.example.utilities.LoadingView
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer
import kotlinx.coroutines.delay
import kotlin.collections.orEmpty

@Composable
fun SearchView(
    navController: NavController ,
   viewModel: SearchViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsState().value
    val colorScheme = MaterialTheme.colorScheme
    var page by rememberSaveable() {  mutableIntStateOf(1)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(vertical = 60.dp, horizontal = 10.dp) ,
             horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp) ,
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
               imageVector =  Icons.AutoMirrored.Filled.ArrowBack ,
                contentDescription = "" ,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.navigate(AppRoutes.MainRoute())
                    },
                tint = colorScheme.onBackground
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .height(60.dp)
                    .background(Color.Transparent, RoundedCornerShape(10.dp))
                    .border(1.dp, colorScheme.onBackground, RoundedCornerShape(26.dp))
            ) {
                CustomTextField(
                    hintText = stringResource(R.string.search_hint),
                    text = state.search?:"",
                    onValueChange = {
                        viewModel.doAction(SearchEvent.onSearchChangeing(it))
                    },
                    hidePassword = null,
                    width = 400.dp,
                    isSearchBar = true,
                    onSearchClick = {
                        viewModel.doAction(SearchEvent.onSearchClick(state.search?:"" , page))
                    },
                    isPassword = false,
                    isEdit = false
                )


            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            when(state.apiState){
                is Resources.Error -> ErrorView { viewModel.doAction(SearchEvent.onSearchClick(state.search?:"", page)) }
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
                is Resources.Success<SearchResponse> -> {
                    val data = state.apiState.data?.results ?: emptyList()
                    LazyVerticalGrid (
                        columns = GridCells.Fixed(2)  ,
                        verticalArrangement = Arrangement.spacedBy(10.dp) ,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)

                    ) {

                        items(data){
                            Log.e("Search" ,"$it")
                            SearchItem(
                                item = it,
                            ) {
                                navController.navigate(
                                    AppRoutes.TvDetailsRoute(it.id?: 0,
                                        if (it.mediaType.equals("tv")) "TV" else "Movie")
                                )

                            }

                        }
                        item {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(200.dp)
                                    .background(colorScheme.onBackground , RoundedCornerShape(12.dp))
                                    .clickable{
                                        page += 1
                                        viewModel.doAction(SearchEvent.onSearchClick(state.search?:"" , page))

                                    }
                            ){

                                Text(
                                    "Load More" ,
                                    style = AppTypography.titleLarge.copy(color = colorScheme.background)
                                )
                            }
                        }


                        }


                }
                Resources.idle -> SearchIdleView(state,navController)
            }

        }


    }
}