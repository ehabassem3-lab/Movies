package com.example.movies.ui.main.search

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movies.network.response.search.ResultsItem

@Composable
fun RecommendationRow(
    onSeeAllClick:() -> Unit  ,
    moviesList : List<ResultsItem?>
){
    val  colorScheme = MaterialTheme.colorScheme

    Column (
        modifier = Modifier.fillMaxWidth().height(350.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text("Watch Together ")
            Text("View ALL ")


        }
        LazyRow(
            modifier = Modifier.fillMaxSize() ,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(moviesList){
                MovieItem(it!!){

                }

            }
        }

    }

}