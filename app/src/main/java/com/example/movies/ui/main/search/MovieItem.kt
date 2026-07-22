package com.example.movies.ui.main.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.util.Logger
import com.example.movies.R
import com.example.movies.mapper.toDiscoverItem
import com.example.movies.network.response.Genres
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.search.ResultsItem
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.home.HomeViewModel
import com.example.movies.ui.main.tabs.home.MovieSectionUiState
import com.example.movies.ui.main.tabs.home.TvSectionUiState
import com.example.movies.ui.main.tabs.saved.FavEvents
import com.example.movies.ui.main.tabs.saved.SavedViewModel
import com.example.movies.ui.main.tabs.saved.sharedSavedViewModel
import com.example.movies.ui.theme.AppTypography

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun MovieItem(
    movieItem : MoviesItem? = null ,
    tvItem : DiscoverItem? = null ,
            onMovieClick : () -> Unit
){
     val tvGenres =listOf(
         Genres(35 , R.string.comedy),
         Genres(18 , R.string.drama),
         Genres(16 , R.string.animation),
         Genres(80 , R.string.crime),
         Genres(10759 , R.string.action_adventure),
         Genres(10751 , R.string.family),
         Genres(9648 , R.string.mystery),
         Genres(10762 , R.string.kids),
         Genres(10768 , R.string.war_politics)
     )
    val movieGenres =listOf(
        Genres(28 , R.string.action),
        Genres(12 , R.string.adventure),
        Genres(16 , R.string.animation),
        Genres(80 , R.string.crime),
        Genres(35 , R.string.comedy),
        Genres(10751 , R.string.family),
        Genres(9648 , R.string.mystery),
        Genres(10749 , R.string.romance),
        Genres(10752 , R.string.war) ,
        Genres(36 , R.string.history),
        Genres(10402 , R.string.music),
        Genres(27 , R.string.horror)

    )
    val context = LocalContext.current
    val genreList = if (tvItem != null) tvGenres else movieGenres
    val genreIds = if (tvItem != null) tvItem.genreIds else movieItem?.genreIds

    val genreMap = genreList.associateBy({ it.id }, { it.name })

    val genreNamesString = genreIds
        ?.mapNotNull { id -> genreMap[id] }
        ?.joinToString(" . ") { resId -> context.getString(resId) }
        ?: ""

    val savedViewModel = sharedSavedViewModel()
    val  favorites    = (savedViewModel.state.collectAsState().value.allFavState as?  Resources.Success)?.data

    val isFavorite = favorites?.any {
        if (tvItem == null) {
            it.id == movieItem?.id && it.mediaType == "movie"
        } else {
            it.id == tvItem?.id && it.mediaType == "tv"
        }
    } ?: false
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .width(200.dp)
            .height(400.dp)
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
                contentAlignment = Alignment.BottomEnd
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
                Icon(
                    painter = painterResource(
                        if (isFavorite) R.drawable.ic_fav_filled
                        else R.drawable.ic_fav
                    ),
                    contentDescription =  "" ,
                    tint = Color.White
                    ,
                    modifier = Modifier
                        .padding(20.dp)
                        .size(20.dp)
                        .clickable {

                                if (tvItem == null) {
                                    savedViewModel.doAction(
                                        FavEvents.addToFavoutire(
                                            mediaId = movieItem?.id!!,
                                            mediaType = "movie",
                                            favorite = !isFavorite,
                                            item =  movieItem.toDiscoverItem()
                                        )
                                    )

                                } else {
                                    savedViewModel.doAction(
                                        FavEvents.addToFavoutire(
                                            mediaId = tvItem?.id!!,
                                            mediaType = "tv",
                                            favorite =  if (!isFavorite) true else false ,
                                            item =  tvItem
                                        )
                                    )

                                }



                        }
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
        Text(
            text = genreNamesString
        )






    }


}
