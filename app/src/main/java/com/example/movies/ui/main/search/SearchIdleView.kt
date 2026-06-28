package com.example.movies.ui.main.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movies.network.response.search.ResultsItem

@Composable
fun SearchIdleView(navController: NavController , searchApiState : List<ResultsItem?>){
    RecommendationRow(
        onSeeAllClick = {} ,
        moviesList =searchApiState
    )

}
