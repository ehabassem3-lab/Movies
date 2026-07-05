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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.movies.R
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.search.ResultsItem
import com.example.movies.ui.theme.AppTypography

@Composable
fun MovieItem(
    movieItem : MoviesItem? = null ,
    tvItem : DiscoverItem? = null ,
    onMovieClick : () -> Unit ,
){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier

            .padding(horizontal = 8.dp, vertical = 8.dp)
            .width(200.dp)
            .height(380.dp)
            .clickable {
                onMovieClick()
                       } ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(10.dp)) ,
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model =if (movieItem != null) "https://image.tmdb.org/t/p/w500${movieItem?.posterPath}"
                          else "https://image.tmdb.org/t/p/w500${tvItem?.posterPath}"
                    ,
                    contentDescription = null,
                    error = painterResource(R.drawable.ic_video),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                if (movieItem != null) movieItem?.name ?:""  else  tvItem?.name ?:"",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                textAlign = TextAlign.Center,
                style = AppTypography.titleSmall.copy(
                    color  = colorScheme.onBackground ,
                    fontWeight = FontWeight.Bold ,
                    fontSize = 18.sp
                )
            )
        Text(
            if (movieItem != null) "${movieItem?.popularity.toString()}  ⭐" ?:""  else  "${tvItem?.voteAverage.toString()} ⭐ " ?:"",
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            textAlign = TextAlign.Center,
            style = AppTypography.titleSmall.copy(
                color  = colorScheme.onBackground ,
                fontWeight = FontWeight.Bold ,
                fontSize = 18.sp
            )
        )




    }


}
