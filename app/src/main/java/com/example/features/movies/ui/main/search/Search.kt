package com.example.features.movies.ui.main.search

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun SearchView(
    navController: NavController ,

){
    val viewModel  = hiltViewModel<SearchViewModel>()
    val state = viewModel.state.collectAsState().value
    val colorScheme = MaterialTheme.colorScheme


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
                        navController.navigate(AppRoutes.MainRoute)
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
                        viewModel.doAction(SearchEvent.onSearchClick(state.search?:""))
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
                is Resources.Error -> {}
                Resources.Loading -> CircularProgressIndicator()
                is Resources.Success<SearchResponse> -> {
                    val data = state.apiState.data?.results ?: emptyList()
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(data){

                            println(data)


                        }

                }
                }
                Resources.idle ->{
                         EmptyView(stringResource(R.string.empty_view),R.drawable.ic_empty_search
                         )


                }

            }

        }


    }
}