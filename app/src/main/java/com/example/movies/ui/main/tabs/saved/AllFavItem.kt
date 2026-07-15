package com.example.movies.ui.main.tabs.saved

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.movies.R
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.profile.FavouriteItem
import com.example.movies.ui.theme.AppTypography

@Composable
fun AllSavItem(
    item : FavouriteItem  ,
    onMovieClick : () -> Unit
){
//    val colorScheme = MaterialTheme.colorScheme
//    Column(
//        modifier = Modifier
//
//            .padding(horizontal = 8.dp, vertical = 8.dp)
//            .width(200.dp)
//            .height(380.dp)
//            .clickable {
//                onMovieClick()
//            } ,
//        verticalArrangement = Arrangement.Center ,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(280.dp)
//                .clip(RoundedCornerShape(10.dp)) ,
//            contentAlignment = Alignment.BottomEnd
//        ) {
//            AsyncImage(
//                model = "https://image.tmdb.org/t/p/w500${item?.poster}"
//
//                ,
//                contentDescription = null,
//                error = painterResource(R.drawable.ic_video),
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(RoundedCornerShape(10.dp))
//            )
//            Icon(
//                painter = painterResource(
//                    if (isFavorite) R.drawable.ic_fav_filled
//                    else R.drawable.ic_fav
//                ),
//                contentDescription =  "" ,
//                tint = Color.White
//                ,
//                modifier = Modifier
//                    .padding(20.dp)
//                    .size(20.dp)
//                    .clickable {
//
//                        if (tvItem == null) {
//                            viewModel.doAction(
//                                HomeEvents.addToFavoutire(
//                                    mediaId = movieItem?.id!!,
//                                    mediaType = "movie",
//                                    favorite = if (!isFavorite) true else false
//                                )
//                            )
//
//                        } else {
//                            viewModel.doAction(
//                                HomeEvents.addToFavoutire(
//                                    mediaId = tvItem?.id!!,
//                                    mediaType = "tv",
//                                    favorite =  if (!isFavorite) true else false
//                                )
//                            )
//
//                        }
//
//
//
//                    }
//            )
//        }
//        Spacer(modifier = Modifier.size(8.dp))
//        Text(
//            if (movieItem != null) movieItem?.name ?:""  else  tvItem?.name ?:"",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(20.dp),
//            textAlign = TextAlign.Center,
//            style = AppTypography.titleSmall.copy(
//                color  = colorScheme.onBackground ,
//                fontWeight = FontWeight.Bold ,
//                fontSize = 18.sp
//            )
//        )
//        Text(
//            if (movieItem != null) "${movieItem?.popularity.toString()}  ⭐" ?:""  else  "${tvItem?.voteAverage.toString()} ⭐ " ?:"",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(20.dp),
//            textAlign = TextAlign.Center,
//            style = AppTypography.titleSmall.copy(
//                color  = colorScheme.onBackground ,
//                fontWeight = FontWeight.Bold ,
//                fontSize = 18.sp
//            )
//        )
//
//
//
//
//    }

}