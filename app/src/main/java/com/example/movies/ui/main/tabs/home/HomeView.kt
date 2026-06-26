package com.example.movies.ui.main.tabs.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeView(){
    val colorScheme = MaterialTheme.colorScheme
   val viewModel = hiltViewModel<HomeViewModel>()
   val state = viewModel.state.collectAsState().value
    LaunchedEffect(Unit){

        viewModel.getDiscoverTv()

    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}