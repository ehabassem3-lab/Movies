package com.example.movies.ui.main.tabs.home

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movies.R
import com.example.movies.mapper.toDiscoverItem
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
import com.google.gson.annotations.Until

@SuppressLint("UnrememberedGetBackStackEntry")
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
    val savedViewModel = sharedSavedViewModel()
    val savedState = savedViewModel.state.collectAsState().value

    val favorites =
        (savedState.allFavState as? Resources.Success)?.data

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
        modifier = Modifier.fillMaxSize().background(colorScheme.background).padding(10.dp) ,
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
                        .clickable{
                        navController.popBackStack()
                    }
                )
                Text(
                    itemTv?.name?: itemMovie?.name ?:"" ,
                    style = AppTypography.titleLarge,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.Center).width(300.dp).height(50.dp),

                    )


            }
        }
    ) {

        Column (
            modifier = Modifier.padding(it) ,

        ){

            LazyColumn (
                modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize()  ,
                horizontalAlignment = Alignment.Start

            ) {


                   item{
                       Box(
                           contentAlignment = Alignment.TopEnd,
                           modifier = Modifier.fillMaxWidth(.99f).height(400.dp).clip(RoundedCornerShape(12.dp)) ,
                       ) {

                           AsyncImage(
                               if (itemTv!= null)  "$posterUrl${itemTv.posterPath}"  else  "$posterUrl${itemMovie?.posterPath}",
                               contentDescription = "" ,
                               modifier = Modifier.padding(vertical = 20.dp).fillMaxSize().clip(RoundedCornerShape(12.dp)) ,
                               contentScale = ContentScale.FillBounds ,
                               alignment = Alignment.Center

                           )
                           Icon(
                               if (isFavorite)painterResource(R.drawable.ic_fav_filled)
                               else painterResource(R.drawable.ic_fav),
                               contentDescription = "" ,
                               modifier = Modifier.padding(40.dp).size(30.dp).clickable {
                                   savedViewModel.doAction(
                                       FavEvents.addToFavoutire(
                                           mediaId = id,
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
                                   }else{
                                       savedViewModel.doAction(FavEvents.OnGetFavouriteTv)

                                   }
                               } ,
                               tint = Color.White
                           )
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