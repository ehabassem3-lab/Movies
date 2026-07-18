package com.example.movies.ui.main.tabs.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.unit.dp
import coil3.Image
import coil3.compose.AsyncImage
import com.example.movies.R
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.cast.CastItem
import com.example.movies.ui.theme.AppTypography

@Composable
fun CrewItem(
    cast: CastItem
){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        Modifier.width(150.dp).height(170.dp) ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(10.dp).size(120.dp).clip(CircleShape)
        ){
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${cast.profilePath}" ,
                contentDescription = "" ,
                modifier = Modifier.fillMaxSize(),
                error = painterResource(R.drawable.ic_splash) ,
                contentScale = ContentScale.FillBounds
            )

        }

        Text(
            cast.name?:""  ,
             style = AppTypography.titleSmall.copy(
                 color = colorScheme.onBackground )
        )
    }


}