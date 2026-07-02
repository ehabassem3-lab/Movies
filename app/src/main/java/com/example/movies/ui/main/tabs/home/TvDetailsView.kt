package com.example.movies.ui.main.tabs.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tv.TvEvents
import com.example.movies.ui.main.tv.TvViewModel
import com.google.gson.annotations.Until

@Composable
fun TvDetailsView(
    id : Int ,
    type : String
){
    val colorScheme = MaterialTheme.colorScheme
    val viewModel = hiltViewModel<TvViewModel>()
    val state = viewModel.state.collectAsState().value
    val itemMovie = (state.moviesApi as? Resources.Success )?.data
    val itemTv = (state.tvApi as? Resources.Success )?.data

    LaunchedEffect(Unit) {
        when(type){
            "Movie" -> viewModel.doAction(TvEvents.GetMovie(id))
            "TV" -> viewModel.doAction(TvEvents.GetTv(id))
        }

    }
    Column(
        modifier = Modifier.fillMaxSize().background(colorScheme.background)
    ) {
         println(itemTv)
    }

}