package com.example.movies.ui.main.tabs.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
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
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movies.R
import com.example.movies.mapper.toDiscoverItem
import com.example.movies.network.response.Genres
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.saved.FavEvents
import com.example.movies.ui.main.tabs.saved.SavedViewModel
import com.example.movies.ui.main.tabs.saved.sharedSavedViewModel
import com.example.movies.ui.main.tv.TvEvents
import com.example.movies.ui.main.tv.TvViewModel
import com.example.movies.ui.theme.AppTypography
import com.example.utilities.EmptyStar
import com.example.utilities.FullStar
import com.example.utilities.HalfStar
import com.google.gson.annotations.Until

@SuppressLint("UnrememberedGetBackStackEntry", "LocalContextGetResourceValueCall")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TvDetailsView(
    id : Int ,
    type : String ,
    navController: NavController ,
){
    val colorScheme = MaterialTheme.colorScheme
    val viewModel = hiltViewModel<TvViewModel>()
    val state = viewModel.state.collectAsState().value
    val itemMovie = (state.moviesApi as? Resources.Success )?.data
    val itemTv = (state.tvApi as? Resources.Success )?.data
    val posterUrl = "https://image.tmdb.org/t/p/w500"
    val videoLink = if (itemTv == null) itemMovie?.videos?.results?.firstOrNull{
        it.site == "YouTube" && it.type == "Trailer"
    }else itemTv.videos?.results?.firstOrNull{
            it.site == "YouTube" && it.type == "Trailer"
        }


    val savedViewModel = sharedSavedViewModel()
    val savedState = savedViewModel.state.collectAsState().value
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
    val genreList = if (itemTv != null) tvGenres else movieGenres
    val genreMap = genreList.associateBy({ it.id }, { it.name })

    val genreNamesString = (itemTv?.genres ?: itemMovie?.genres)
        ?.mapNotNull { it.name }
        ?.joinToString(" . ")
        ?: (itemTv?.genreIds ?: itemMovie?.genreIds)
            ?.mapNotNull { id -> genreMap[id] }
            ?.joinToString(" . ") { resId -> context.getString(resId) }
        ?: ""


    val rated = (savedViewModel.state.collectAsState().value.allRatedState as? Resources.Success )?.data
    val rateds = (savedViewModel.state.collectAsState().value.RatedMoviesState as? Resources.Success )?.data?.results


    val isRated = rated?.any{
        if (itemTv == null){
            it.id == itemMovie?.id && it.mediaType == "movie"
        }else{
            it.id ==itemMovie?.id && it.mediaType == "tv"
        }
    }?:false
    val favorites =
        (savedState.allFavState as? Resources.Success)?.data
    val watchlist = (savedState.allWatchListState as?  Resources.Success)?.data
      val isWatchList = watchlist?.any{
          it.id == id && it.mediaType == type.lowercase()
      } ?: false
    val isFavorite = favorites?.any {
        it.id == id && it.mediaType == type.lowercase()
    } ?: false

     LaunchedEffect(Unit) {
         when(type){
             "Movie" -> viewModel.doAction(TvEvents.getDetails(id,"Movie"))
             "TV" -> viewModel.doAction(TvEvents.getDetails(id,"Tv"))
         }

     }

    LaunchedEffect(Unit) {
        when(type){
            "Movie" -> viewModel.doAction(TvEvents.getMoviesCast(id))
            "TV" -> viewModel.doAction(TvEvents.getTvCast(id))
        }

    }

    LaunchedEffect(Unit) {
        when(type){
            "Movie" -> viewModel.doAction(TvEvents.GetMovie(id))
            "TV" -> viewModel.doAction(TvEvents.GetTv(id))
        }

    }
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(10.dp) ,
        topBar = {
            Box(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                    contentDescription = "" ,
                    tint = colorScheme.onBackground ,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(24.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    itemTv?.name?: itemMovie?.name ?:"" ,
                    style = AppTypography.titleLarge,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(300.dp)
                        .height(50.dp),

                    )


            }
        }
    ) {

        Column (
            modifier = Modifier.padding(it) ,

        ){


            LazyColumn (
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize()  ,
                horizontalAlignment = Alignment.Start

            ) {


                   item{
                       Box(
                           contentAlignment = Alignment.TopEnd,
                           modifier = Modifier
                               .fillMaxWidth(.99f)
                               .height(400.dp)
                               .clip(RoundedCornerShape(12.dp)) ,
                       ) {

                           AsyncImage(
                               if (itemTv!= null)  "$posterUrl${itemTv.posterPath}"  else  "$posterUrl${itemMovie?.posterPath}",
                               contentDescription = "" ,
                               modifier = Modifier
                                   .padding(vertical = 20.dp)
                                   .fillMaxSize()
                                   .clip(RoundedCornerShape(12.dp)) ,
                               contentScale = ContentScale.FillBounds ,
                               alignment = Alignment.Center

                           )
                           Box(
                               modifier = Modifier
                                   .size(30.dp)
                                   .offset(x = -330.dp, y = 30.dp)
                                   .background(colorScheme.onBackground, RoundedCornerShape(12.dp)),
                               contentAlignment = Alignment.Center
                           ){
                               Icon(
                                   painter =  if (isWatchList) painterResource(R.drawable.ic_un_save)
                                   else painterResource(R.drawable.ic_save) ,
                                   contentDescription = "" ,
                                   tint = colorScheme.background ,
                                   modifier = Modifier
                                       .size(20.dp)
                                       .clickable {
                                           savedViewModel.doAction(
                                               FavEvents.onAddToWatchList(
                                                   mediaId = id,
                                                   mediaType = type.lowercase(),
                                                   watchList = !isWatchList,
                                                   item = itemTv ?: itemMovie?.toDiscoverItem()
                                               )
                                           )
                                       }
                               )
                           }
                               Box(
                                   modifier = Modifier
                                       .size(30.dp)
                                       .offset(y = 30.dp, x = -10.dp)
                                       .background(
                                           colorScheme.onBackground,
                                           RoundedCornerShape(12.dp)
                                       ) ,
                                   contentAlignment = Alignment.Center
                               ) {
                                   Icon(
                                       if (isFavorite)painterResource(R.drawable.ic_fav_filled)
                                       else painterResource(R.drawable.ic_fav),
                                       contentDescription = "" ,
                                       modifier = Modifier
                                           .size(20.dp)
                                           .clickable {
                                               savedViewModel.doAction(
                                                   FavEvents.addToFavoutire(
                                                       mediaId = if (itemTv == null) itemMovie?.id!! else itemTv.id!!,
                                                       mediaType = type.lowercase(),
                                                       favorite = !isFavorite,
                                                       item = if (type == "Movie")
                                                           itemMovie?.toDiscoverItem()
                                                       else
                                                           itemTv
                                                   )
                                               )
                                               if (type == "Movie") {
                                                   savedViewModel.doAction(FavEvents.OnGetFavouriteMovie)
                                               } else {
                                                   savedViewModel.doAction(FavEvents.OnGetFavouriteTv)

                                               }
                                           } ,
                                       tint = colorScheme.background
                                   )
                               }


                       }
                   }
                item{
                    Column (
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                            .height(150.dp) ,
                        verticalArrangement = Arrangement.Center ,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier =  Modifier.fillMaxWidth() ,
                            horizontalArrangement = Arrangement.SpaceAround
                        ){
                            Text(
                                stringResource(R.string.genres) ,
                                modifier = Modifier.offset(x= -50.dp) ,

                                        style = AppTypography.titleLarge.copy(
                                    color = colorScheme.onBackground  ,
                                    fontSize = 32.sp

                                )
                            )
                            Box(
                                modifier = Modifier
                                    .offset(x = 30.dp)
                                    .size(50.dp)
                                    .background(colorScheme.onBackground, RoundedCornerShape(12.dp))
                                    .clickable {
                                        val youtubeUrl = videoLink?.key?.let {
                                            "https://www.youtube.com/watch?v=$it"
                                        }
                                        youtubeUrl?.let { url ->
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                            context.startActivity(intent)
                                        }
                                    },

                                contentAlignment = Alignment.Center
                            ){
                                Icon(
                                    painter = painterResource(R.drawable.ic_play) ,
                                    contentDescription = "" ,
                                    tint = colorScheme.background ,
                                    modifier = Modifier.size(20.dp)

                                    )
                            }

                        }


                        Text(
                            genreNamesString ,
                            modifier =  Modifier
                                .padding(vertical = 10.dp, horizontal = 20.dp)
                                .fillMaxWidth()
                                .height(200.dp),
                            style = AppTypography.titleSmall.copy(
                                color = colorScheme.onBackground ,
                                fontSize = 20.sp
                             )
                        )


                    }
                }
                item{
                    if (isRated){
                        Column(
                            modifier = Modifier
                                .padding(end = 40.dp)
                                .offset(y = -50.dp)
                                .width(350.dp)
                                .fillMaxHeight()

                            ) {
                             Text(
                                 "Your Rate is  :  ${savedState.Rate}" ,
                                 style = AppTypography.titleLarge.copy(
                                 )
                             )
                            val rating = (savedState.Rate / 2).toFloat()
                                 Row (){

                                          repeat(5) { index ->
                                              when {
                                                  index + 1 <= rating -> {
                                                      FullStar()
                                                  }

                                                  index + 0.5f <= rating -> {
                                                      HalfStar()
                                                  }

                                                  else -> {
                                                      EmptyStar()
                                                  }
                                              }
                                          }

                                  }

                            }
                    }else{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp) ,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 40.dp)
                                    .offset(y = -50.dp)
                                    .width(150.dp)
                                    .fillMaxHeight()
                                    .background(colorScheme.onBackground, RoundedCornerShape(10.dp))
                                    .clickable {
                                    }
                                    .clickable {
                                        if (itemTv == null) {
                                            savedViewModel.doAction(
                                                FavEvents.onRateMovie(
                                                    id = itemMovie?.id!!,
                                                    rate = savedState.Rate,
                                                    item = itemMovie.toDiscoverItem(),
                                                    rated = true,
                                                    mediaType = "movie"
                                                )
                                            )
                                        } else {
                                            savedViewModel.doAction(
                                                FavEvents.onRateTv(
                                                    id = itemTv.id!!,
                                                    rate = savedState.Rate,
                                                    item = itemTv,
                                                    rated = true,
                                                    mediaType = "tv"
                                                )
                                            )

                                        }
                                    } ,
                                contentAlignment = Alignment.Center
                            ){
                                Text(
                                    "Rate" ,
                                    style = AppTypography.titleMedium.copy(
                                        color = colorScheme.background
                                    )
                                )
                            }
                            Row (
                                modifier = Modifier
                                    .offset(y = -50.dp)
                                    .fillMaxHeight()
                                    .width(150.dp)
                                    .background(colorScheme.onBackground, RoundedCornerShape(10.dp)) ,
                                horizontalArrangement = Arrangement.SpaceAround ,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .background(
                                            colorScheme.background,
                                            RoundedCornerShape(10.dp)
                                        )
                                        .clickable {
                                            savedViewModel.doAction(FavEvents.onRateChange("plus"))
                                        } ,
                                    contentAlignment = Alignment.Center
                                ){
                                    Icon(
                                        painterResource(R.drawable.ic_plus) ,
                                        contentDescription = "" ,
                                        tint = colorScheme.onBackground,
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Text(
                                    text = (savedState.Rate).toString() ,
                                    style = AppTypography.titleLarge.copy(
                                        color =colorScheme.background
                                    )
                                )
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .background(
                                            colorScheme.background,
                                            RoundedCornerShape(10.dp)
                                        )
                                        .clickable {
                                            savedViewModel.doAction(FavEvents.onRateChange("minus"))

                                        } ,
                                    contentAlignment = Alignment.Center
                                ){
                                    Icon(
                                        painterResource(R.drawable.ic_minus) ,
                                        contentDescription = "" ,
                                        tint = colorScheme.onBackground,
                                        modifier = Modifier.size(15.dp)
                                    )
                                }


                            }
                        }

                    }

                }
                item{
                    Text(
                        itemTv?.overview?: itemMovie?.overview ?:"" ,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )

                }


          item {

                  Text(
                      "Cast" ,
                      style =  AppTypography.titleLarge.copy(color = colorScheme.onBackground , fontWeight = FontWeight.Bold , fontSize = 36.sp),
                      modifier = Modifier.padding(vertical = 20.dp) ,
                  )


              if (type == "Movie"){
                  when(state.castStateMovies){
                      is Resources.Error -> {}
                      Resources.Loading -> {}
                      is Resources.Success<Cast> -> {
                          val castList = state.castStateMovies.data?.cast ?: emptyList()
                          LazyRow() {
                              items(castList){
                                  CrewItem(it!!){navController.navigate(AppRoutes.ActorRoute(it.id!!))}


                              }
                          }
                      }
                      Resources.idle ->{}
                  }

              }
              else{
                  when(state.castStateTv){
                      is Resources.Error -> {}
                      Resources.Loading -> {}
                      is Resources.Success<Cast> ->{
                          val castList = state.castStateTv.data?.cast ?: emptyList()
                          LazyRow() {
                              items(castList){
                                  CrewItem(it!!){ navController.navigate(AppRoutes.ActorRoute(it.id!!)) }

                              }
                          }
                      }
                      Resources.idle -> {}
                  }

              }

          }

            }







        }




    }

}