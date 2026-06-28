package com.example.movies.ui.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movies.R
import com.example.movies.network.response.search.ResultsItem
import com.example.movies.ui.theme.AppTypography

@Composable
fun MovieItem(
    movieItem : ResultsItem ,
    onMovieClick : () -> Unit ,
){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier.width(100.dp).height(220.dp).clickable{
            // Navigate to The Moview Deatails
        } ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(15.dp)) ,
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movieItem.posterPath}",
                contentDescription = null,
                error = painterResource(R.drawable.ic_video),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(15.dp))
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            movieItem.title ?:"" ,
            style = AppTypography.titleSmall.copy(
                color  = colorScheme.onBackground ,
                fontWeight = FontWeight.Light
            )
            )

    }


}
