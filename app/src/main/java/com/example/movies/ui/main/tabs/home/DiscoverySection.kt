package com.example.movies.ui.main.tabs.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography

@Composable
fun DiscoverySection(
    apiState : Resources<DiscoverResponse>
){
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .height(3000.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Discover Tv Shows" ,
            style = AppTypography.titleLarge.copy(color = colorScheme.onBackground) ,
            textAlign = TextAlign.Start
        )
        when(apiState){
            is Resources.Error ->{}
            Resources.Loading -> CircularProgressIndicator()
            is Resources.Success<DiscoverResponse> ->{
                val list = apiState.data?.results?:emptyList()
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically ,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                     items(list){
                         MovieItem(it !!)

                     }
                }
            }
            Resources.idle -> {}
        }
    }

}