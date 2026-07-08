package com.example.movies.ui.main.tabs.home

import android.R
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tv.TvEvents
import com.example.movies.ui.main.tv.TvViewModel
import com.example.movies.ui.theme.AppTypography
import com.google.gson.annotations.Until

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TvDetailsView(
    id : Int ,
    type : String ,
    navController: NavController
){
    val colorScheme = MaterialTheme.colorScheme
    val viewModel = hiltViewModel<TvViewModel>()
    val state = viewModel.state.collectAsState().value
    val itemMovie = (state.moviesApi as? Resources.Success )?.data
    val itemTv = (state.tvApi as? Resources.Success )?.data
    val posterUrl = "https://image.tmdb.org/t/p/w500"

    LaunchedEffect(Unit) {
        when(type){
            "Movie" -> viewModel.doAction(TvEvents.GetMovie(id))
            "TV" -> viewModel.doAction(TvEvents.GetTv(id))
        }

    }
    val item = itemTv ?: itemMovie
    Scaffold (
        modifier = Modifier.fillMaxSize().background(colorScheme.background)
    ) {
        println(item)
        Column (
            modifier = Modifier.padding(it)
        ){
            Row(
                modifier = Modifier.fillMaxWidth().height(60.dp) ,
                 verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                    contentDescription = "" ,
                     tint = colorScheme.onBackground ,
                    modifier = Modifier.size(24.dp).clickable{
                         navController.popBackStack()
                    }
                )
                Text(
                     itemTv?.name?: itemMovie?.title ?:"" ,
                    style = AppTypography.titleLarge
                )
                Text("")


            }
            Column(
                modifier = Modifier.fillMaxSize()  ,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(.95f).height(500.dp).clip(RoundedCornerShape(10.dp)) ,
//                    contentAlignment = Alignment.Center

                ) {
                    GlideImage(
                        if (itemTv!= null)  "$posterUrl${itemTv.posterPath}"  else  "$posterUrl${itemMovie?.posterPath}",
                        contentDescription = "" ,
                        modifier = Modifier.fillMaxSize() ,
                        contentScale = ContentScale.FillBounds ,
                        alignment = Alignment.Center

                    )
//                    Icon(
//                        painter = painterResource()
//                    )


                }

            }






        }




    }

}